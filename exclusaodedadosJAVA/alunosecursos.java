import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Nome:Joao carlos

public class alunosecursos {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/nomedobanco";
        final String user = "nome de usuario";
        final String password = "senha";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            int idAluno = 1; // Id do aluno a ser excluído

            // Remove todas as associações do aluno com cursos
            String sql = "DELETE FROM cursos WHERE aluno_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idAluno);
                pstmt.executeUpdate();
            }

            // Exclui o aluno
            sql = "DELETE FROM alunos WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idAluno);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Aluno e associações excluídos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir aluno e associações: " + e.getMessage());
        }
    }
}
}

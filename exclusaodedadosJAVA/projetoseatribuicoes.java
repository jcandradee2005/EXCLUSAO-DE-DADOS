import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Nome:Joao Carlos

public class projetoseatribuicoes {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/nomedobanco";
        final String user = "nome de usuario";
        final String password = "senha";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            int idProjeto = 1; // Id do projeto a ser excluído

            // Remove todas as atribuições do projeto
            String sql = "DELETE FROM atribuicoes WHERE projeto_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idProjeto);
                pstmt.executeUpdate();
            }

            // Exclui o projeto
            sql = "DELETE FROM projetos WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idProjeto);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Projeto e atribuições excluídos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir projeto e atribuições: " + e.getMessage());
        }
    }
}


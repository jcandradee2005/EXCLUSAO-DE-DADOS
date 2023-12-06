import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//nome:Joao Carlos

public class resultadosexamesepacientes {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/nomedobanco";
        final String user = "nome de usuario";
        final String password = "senha";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            int idResultadoExame = 1; // Id do resultado de exame a ser excluído

            // Remove todas as associações do resultado de exame com pacientes
            String sql = "DELETE FROM pacientes WHERE id = (SELECT paciente_id FROM resultados_exames WHERE id = ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idResultadoExame);
                pstmt.executeUpdate();
            }

            // Exclui o resultado de exame
            sql = "DELETE FROM resultados_exames WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idResultadoExame);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Resultado de exame e paciente associado excluídos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir resultado de exame e paciente associado: " + e.getMessage());
        }
    }
}


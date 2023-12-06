import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Nome:Joao Carlos

public class eventoseparticipantes {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/nomedobanco";
        final String user = "nome de usuario";
        final String password = "senha";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            int idEvento = 1; // Id do evento a ser excluído

            // Remove todas as associações do evento com participantes
            String sql = "DELETE FROM participantes WHERE evento_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idEvento);
                pstmt.executeUpdate();
            }

            // Exclui o evento
            sql = "DELETE FROM eventos WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idEvento);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Evento e participantes associados excluídos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir evento e participantes associados: " + e.getMessage());
        }
    }
}


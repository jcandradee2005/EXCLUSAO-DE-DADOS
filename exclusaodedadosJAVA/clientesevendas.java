import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Nome:Joao Carlos

public class clientevendas {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/nomedobanco";
        final String user = "nome de usuario";
        final String password = "senha";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            int idCliente = 1; // Id do cliente a ser excluído

            // Remove todas as vendas do cliente
            String sql = "DELETE FROM vendas WHERE cliente_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idCliente);
                pstmt.executeUpdate();
            }

            // Exclui o cliente
            sql = "DELETE FROM clientes WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idCliente);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Cliente e vendas associadas excluídos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente e vendas associadas: " + e.getMessage());
        }
    }
}
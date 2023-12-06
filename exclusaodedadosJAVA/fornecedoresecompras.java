import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Nome:Joao Carlos

public class fornecedoresecompras {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/nomedobanco";
        final String user = "nome de usuario";
        final String password = "senha";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            int idFornecedor = 1; // Id do fornecedor a ser excluído

            // Remove todas as compras realizadas pelo fornecedor
            String sql = "DELETE FROM compras WHERE fornecedor_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idFornecedor);
                pstmt.executeUpdate();
            }

            // Exclui o fornecedor
            sql = "DELETE FROM fornecedores WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idFornecedor);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Fornecedor e compras excluídos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir fornecedor e compras: " + e.getMessage());
        }
    }
}


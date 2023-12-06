import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Nome:Joao Carlos

public class produtoecategoria {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/nomedobanco";
        final String user = "nome de usuario";
        final String password = "senha";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            int idProduto = 1; // Id do produto a ser excluído
            int idCategoria = 2; // Id da categoria a ser excluída

            // Exclui a categoria
            String sql = "DELETE FROM categorias WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idCategoria);
                pstmt.executeUpdate();
            }

            // Remove as associações do produto com categorias
            sql = "DELETE FROM categorias_produtos WHERE produto_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idProduto);
                pstmt.executeUpdate();
            }

            // Exclui o produto
            sql = "DELETE FROM produtos WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idProduto);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Produto, categoria e associações excluídos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir produto, categoria e associações: " + e.getMessage());
        }
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Nome:Joao Carlos

public class livroseautores {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/nomedobanco";
        final String user = "nome de usuario";
        final String password = "senha";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            int idLivro = 1; // Id do livro a ser excluído

            // Remove todas as associações do livro com autores
            String sql = "DELETE FROM autores_livros WHERE livro_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idLivro);
                pstmt.executeUpdate();
            }

            // Exclui o livro
            sql = "DELETE FROM livros WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idLivro);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Livro e autores associados excluídos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir livro e autores associados: " + e.getMessage());
        }
    }
}


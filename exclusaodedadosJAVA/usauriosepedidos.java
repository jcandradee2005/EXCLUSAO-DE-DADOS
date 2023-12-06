import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//nome:Joao Carlos

public class usuarioepedido {
  
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);

            // Exclui pedidos associados ao usuário específico
            String sql = "DELETE FROM pedidos WHERE id_usuario = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_usuario); // Insira o ID do usuário que deseja excluir aqui
            pstmt.executeUpdate();

            // Exclui o usuário específico
            sql = "DELETE FROM usuarios WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_usuario); // Insira o ID do usuário que deseja excluir aqui
            pstmt.executeUpdate();

            conn.commit();

            System.out.println("Usuário e pedidos associados excluídos com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro: " + e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro: " + e.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

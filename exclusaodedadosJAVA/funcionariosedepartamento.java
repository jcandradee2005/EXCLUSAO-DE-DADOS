import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Nome:Joao Carlos

public class funcionariosedepartamento {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/nomedobanco";
        final String user = "nome de usuario";
        final String password = "senha";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            int idFuncionario = 1; // Id do funcionário a ser excluído

            // Remove todas as associações do funcionário com departamentos
            String sql = "DELETE FROM departamentos WHERE funcionario_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idFuncionario);
                pstmt.executeUpdate();
            }

            // Exclui o funcionário
            sql = "DELETE FROM funcionarios WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idFuncionario);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Funcionário e associações a departamentos excluídos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionário e associações a departamentos: " + e.getMessage());
        }
    }
}


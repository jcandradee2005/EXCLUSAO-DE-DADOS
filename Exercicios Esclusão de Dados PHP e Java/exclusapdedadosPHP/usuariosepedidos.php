<?php // nome: maxwell

$host_name = "localhost";
$user_name = "root";
$password = "";
$db_name = "nome_da_base_de_dados";

$conexao = new mysqli($host_name, $user_name, $password, $db_name);

if ($conexao->connect_error) {
    die("Erro na conexão: " . $conexao->connect_error);
}

$id_usuario = 1; // Insira o ID do usuário que deseja excluir aqui

$sql = "DELETE FROM pedidos WHERE id_usuario = ?";
$stmt = $conexao->prepare($sql);
$stmt->bind_param("i", $id_usuario);

if ($stmt->execute()) {
    $sql = "DELETE FROM usuarios WHERE id = ?";
    $stmt = $conexao->prepare($sql);
    $stmt->bind_param("i", $id_usuario);

    if ($stmt->execute()) {
        echo "Usuário e pedidos associados excluídos com sucesso!";
    } else {
        echo "Erro: " . $stmt->error;
    }
} else {
    echo "Erro: " . $stmt->error;
}

$stmt->close();
$conexao->close();

?>
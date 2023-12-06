<?php // nome: maxwell

$host_name = "localhost";
$user_name = "root";
$password = "";
$db_name = "nome_da_base_de_dados";

$conexao = new mysqli($host_name, $user_name, $password, $db_name);

if ($conexao->connect_error) {
    die("Erro na conexão: " . $conexao->connect_error);
}

$id_cliente = 1; // Insira o ID do cliente que deseja excluir aqui

$sql = "DELETE FROM vendas WHERE id_cliente = ?";
$stmt = $conexao->prepare($sql);
$stmt->bind_param("i", $id_cliente);

if ($stmt->execute()) {
    $sql = "DELETE FROM clientes WHERE id = ?";
    $stmt = $conexao->prepare($sql);
    $stmt->bind_param("i", $id_cliente);

    if ($stmt->execute()) {
        echo "Cliente e vendas associadas excluídos com sucesso!";
    } else {
        echo "Erro: " . $stmt->error;
    }
} else {
    echo "Erro: " . $stmt->error;
}

$stmt->close();
$conexao->close();

?>
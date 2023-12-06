<?php //nome: maxwell

$host_name = "localhost";
$user_name = "root";
$password = "";
$db_name = "nome_da_base_de_dados";

$conexao = new mysqli($host_name, $user_name, $password, $db_name);

if ($conexao->connect_error) {
    die("Erro na conexão: " . $conexao->connect_error);
}

$id_produto = 1; // Insira o ID do produto que deseja excluir aqui

$sql = "DELETE FROM categorias WHERE id_produto = ?";
$stmt = $conexao->prepare($sql);
$stmt->bind_param("i", $id_produto);

if ($stmt->execute()) {
    $sql = "DELETE FROM produtos WHERE id = ?";
    $stmt = $conexao->prepare($sql);
    $stmt->bind_param("i", $id_produto);

    if ($stmt->execute()) {
        echo "Produto e associações a categorias excluídos com sucesso!";
    } else {
        echo "Erro: " . $stmt->error;
    }
} else {
    echo "Erro: " . $stmt->error;
}

$stmt->close();
$conexao->close();

?>
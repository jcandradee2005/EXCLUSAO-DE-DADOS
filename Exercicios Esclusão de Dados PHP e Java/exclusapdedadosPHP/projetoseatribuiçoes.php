<?php // nome: maxwell

$host_name = "localhost";
$user_name = "root";
$password = "";
$db_name = "nome_da_base_de_dados";

$conexao = new mysqli($host_name, $user_name, $password, $db_name);

if ($conexao->connect_error) {
    die("Erro na conexão: " . $conexao->connect_error);
}

$id_projeto = 1; // Insira o ID do projeto que deseja excluir aqui

$sql = "DELETE FROM atribuicoes WHERE id_projeto = ?";
$stmt = $conexao->prepare($sql);
$stmt->bind_param("i", $id_projeto);

if ($stmt->execute()) {
    $sql = "DELETE FROM projetos WHERE id = ?";
    $stmt = $conexao->prepare($sql);
    $stmt->bind_param("i", $id_projeto);

    if ($stmt->execute()) {
        echo "Projeto e atribuições associadas excluídos com sucesso!";
    } else {
        echo "Erro: " . $stmt->error;
    }
} else {
    echo "Erro: " . $stmt->error;
}

$stmt->close();
$conexao->close();

?>
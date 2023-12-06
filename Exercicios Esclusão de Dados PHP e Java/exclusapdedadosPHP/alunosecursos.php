<?php // nome: maxwell

$host_name = "localhost";
$user_name = "root";
$password = "";
$db_name = "nome_da_base_de_dados";

$conexao = new mysqli($host_name, $user_name, $password, $db_name);

if ($conexao->connect_error) {
    die("Erro na conexão: " . $conexao->connect_error);
}

$id_aluno = 1; // Insira o ID do aluno que deseja excluir aqui

$sql = "DELETE FROM cursos_alunos WHERE id_aluno = ?";
$stmt = $conexao->prepare($sql);
$stmt->bind_param("i", $id_aluno);

if ($stmt->execute()) {
    $sql = "DELETE FROM alunos WHERE id = ?";
    $stmt = $conexao->prepare($sql);
    $stmt->bind_param("i", $id_aluno);

    if ($stmt->execute()) {
        echo "Aluno e cursos associados excluídos com sucesso!";
    } else {
        echo "Erro: " . $stmt->error;
    }
} else {
    echo "Erro: " . $stmt->error;
}

$stmt->close();
$conexao->close();

?>
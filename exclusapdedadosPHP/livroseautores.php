<?php // nome: maxwell
$servername = "localhost";
$username = "USER";
$password = "PASS";
$dbname = "DATABASE";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Erro de conexão: " . $conn->connect_error);
}

$id_livro = 1; // Insira o ID do livro que deseja excluir aqui

try {
    $conn->begin_transaction();

    // Exclui as associações do livro com autores na tabela `autores`
    $sql = "DELETE FROM autores WHERE id_livro = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("i", $id_livro);
    $stmt->execute();

    // Exclui o livro específico da tabela `livros`
    $sql = "DELETE FROM livros WHERE id = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("i", $id_livro);
    $stmt->execute();

    $conn->commit();

    echo "Livro e associações com autores excluídos com sucesso!";

} catch (Exception $e) {
    echo "Erro: " . $e->getMessage();

} finally {
    $stmt->close();
    $conn->close();
}
?>
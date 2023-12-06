<?php // nome: maxwell
$servername = "localhost";
$username = "USER";
$password = "PASS";
$dbname = "DATABASE";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Erro de conexão: " . $conn->connect_error);
}

$id_evento = 1; // ID do evento que você deseja excluir

try {
    $conn->begin_transaction();

    // Exclui os participantes do evento específico na tabela `participantes`
    $sql = "DELETE FROM participantes WHERE id_evento = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("i", $id_evento);
    $stmt->execute();

    // Exclui o evento específico da tabela `eventos`
    $sql = "DELETE FROM eventos WHERE id = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("i", $id_evento);
    $stmt->execute();

    $conn->commit();

    echo "Evento e participantes excluídos com sucesso!";

} catch (Exception $e) {
    echo "Erro: " . $e->getMessage();

} finally {
    $stmt->close();
    $conn->close();
}
?>
<?php // nome: maxwell
$servername = "localhost";
$username = "USER";
$password = "PASS";
$dbname = "DATABASE";

try {
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Iniciar a transação
    $conn->beginTransaction();

    // Excluir o resultado do exame da tabela `resultados_exames`
    $sql = "DELETE FROM resultados_exames WHERE id = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bindParam(1, $id_resultado_exame);
    $stmt->execute();

    // Excluir os dados do paciente associados a esse resultado da tabela `pacientes`
    $sql = "DELETE FROM pacientes WHERE id = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bindParam(1, $id_paciente);
    $stmt->execute();

    // Confirmar a transação
    $conn->commit();

    echo "Resultado do exame e dados do paciente excluídos com sucesso!";

} catch (PDOException $e) {
    echo "Erro: " . $e->getMessage();

} finally {
    $stmt = null;
    $conn = null;
}
?>
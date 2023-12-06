<?php // nome: maxwell
    $host_name = "localhost";
    $user_name = "USER";
    $password = "PASS";
    $db_name = "DATABASE";

    $conn = new mysqli($host_name, $user_name, $password, $db_name);

    if ($conn->connect_error) {
        die("Conexão falhou: " . $conn->connect_error);
    }

    $id_fornecedor = 1; // Insira o ID do fornecedor que deseja excluir aqui

    try {
        $conn->begin_transaction();

        // Exclui compras associadas ao fornecedor específico
        $sql = "DELETE FROM compras WHERE id_fornecedor = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("i", $id_fornecedor);
        $stmt->execute();

        // Exclui o fornecedor específico
        $sql = "DELETE FROM fornecedores WHERE id = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("i", $id_fornecedor);
        $stmt->execute();

        $conn->commit();

        echo "Fornecedor e compras associadas excluídos com sucesso!";

    } catch (Exception $e) {
        echo "Erro: " . $e->getMessage();

    } finally {
        $stmt->close();
        $conn->close();
    }
?>
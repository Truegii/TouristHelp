<?php
	if ($_SERVER['REQUEST_METHOD'] == 'GET') {
		require_once("db.php");

		$result = array();
        $query = "SELECT * FROM zonas WHERE depaz IN ('Ayacucho', 'Junin', 'Cusco', 'Apurimac', 'San Martin', 'Cajamarca', 'Huancavelica', 'Huanuco', 'Puno')";
        $responce = $mysql->query($query);

        while($row = mysqli_fetch_array($responce))
        {
            $index['id']= $row['0'];
            $index['nombre']= $row['1'];
            $index['desc']= $row['2'];
            $index['imgurl']= $row['3'];
            $index['depa']= $row['4'];
            $index['califica']= $row['5'];
            $index['direc']= $row['6'];

            array_push($result, $index);

        }

        echo json_encode($result);

	}
?>
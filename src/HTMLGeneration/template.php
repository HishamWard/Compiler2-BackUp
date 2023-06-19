<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compiler 2</title>
    <style>
    html, body {
        font-family: Jetbrains Mono;
        height: 100%;
    }

    body {
        background: linear-gradient(112.1deg, rgb(32, 38, 57) 11.4%, rgb(63, 76, 119) 70.2%);
        color: whitesmoke;
        position: relative;
        margin: 0;
    }

    .params-container {
        position: absolute;
        top: 20px;
        left: 20px;
        padding: 20px;
        background-color: aliceblue;
        color: black;
        width: max-content;
        border-radius: 20px;
    }

    .params-title {
        margin: 0 0 20px 0;
    }
    </style>
</head>
<body>
<?php

if (!empty($_POST)) {
    echo "<div class='params-container'>";
    echo "<h1 class='params-title'>Widget parameters: </h1>";

    foreach ($_POST as $key => $value) {
        echo "<div style='display: flex; justify-items: space-between; gap: 20px'>";
        echo "$key: $value";
        echo "</div>";

    }
    echo "</div>";
}
?>



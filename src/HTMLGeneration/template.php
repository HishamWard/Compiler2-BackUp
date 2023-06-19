<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compiler 2</title>
    <style>
    html, body {
        font-family: Helvetica, JetBrains Mono;
        height: 100%;
    }

    body {
        background: white;
        position: relative;
        margin: 0;
    }

    p {
     margin: 0
    }

    .params-container {
        position: absolute;
        bottom: 20px;
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

    button, a {
      /* Set button appearance */
      background-color: #2196f3;
      color: #fff;
      border: none;
      border-radius: 4px;
      padding: 8px 16px;
      font-size: 14px;
      font-weight: 500;
      text-transform: uppercase;
      letter-spacing: 1px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.25);
    }

    /* Add hover effect */
    button:hover, a:hover {
      background-color: #1976d2;
    }

    /* Add active effect */
    button:active, a:active {
      background-color: #0d47a1;
    }

    a {
        text-decoration: none
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

    foreach ($_GET as $key => $value) {
        echo "<div style='display: flex; justify-items: space-between; gap: 20px'>";
        echo "$key: $value";
        echo "</div>";
    }

    echo "</div>";
}

if (!empty($_GET)) {
    echo "<div class='params-container'>";
    echo "<h1 class='params-title'>Widget parameters: </h1>";

    foreach ($_GET as $key => $value) {
        echo "<div style='display: flex; justify-items: space-between; gap: 20px'>";
        echo "$key: $value";
        echo "</div>";
    }

    echo "</div>";
}
?>



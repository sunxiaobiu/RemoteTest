<html>
<script>
    function selectDevice() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                document.getElementById("test").innerHTML = xmlhttp.responseText;
            }
        };
        xmlhttp.open("POST", "device/selectDevice", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("id=12345");
    }
</script>
<body>
<h2>Hello World!</h2>

<p id="test">click 'Click Me' button, here will show response if server running</p>
<button type="button" onclick="selectDevice()">Click Me</button>
</body>
</html>
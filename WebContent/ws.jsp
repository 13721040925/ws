<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<h1>获得服务器更新</h1>
<div id="result"></div>

<script>
var msg="magicpeng";
var source=new EventSource("/ws/wsServlet?msg="+msg);
source.onmessage=function(event)
{
  document.getElementById("result").innerHTML+=event.data + "<br />";
};
</script>

</body>
</html>
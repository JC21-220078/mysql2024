<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JK3B06</title>
</head>

<%
 	ArrayList<String[]> item =
 			(ArrayList<String[]> ) request.getAttribute("item");
 %>
 
 <%
 	ArrayList<String[]> item1 =
 			(ArrayList<String[]> ) request.getAttribute("item2");
 %>

<body>
<FORM METHOD="get" ACTION="./product">
<SELECT NAME="ID">
<% for (String[] ss : item) { %>
		<OPTION VALUE="<%= ss[0]  %>">
		<%= ss[1] %>
		</OPTION>
<% } %>

</SELECT>
<INPUT TYPE="submit" VALUE="絞り込む"/>
</FORM>
<table>
すべての商品一覧<br>
<tr>
<th>JANコード</th>
<th>商品名</th>
<th>メーカー名</th>
</tr>
<% for (String[] ss : item1) { %>
	<tr>
		<th> <%= ss[0] %></th>
		<td><%= ss[1] %></td>
		<td><%= ss[2] %></td>
	</tr>
<% } %>
</table>

</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<table>
		<tr>			
			<td><a href="private/admin/index">Login As Administrator</a></td>
		</tr>
		<tr>		
			<td><a href="private/enduser/index">Login As Enduser</a></td>
		</tr>
                <tr>
		<td>
                        <a href="<c:url value="/j_spring_security_logout" />" > Logout</a></td>
                </tr>
	</table>
</body>
</html>
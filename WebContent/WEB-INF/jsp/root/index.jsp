<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Riddler</title>
		
		<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
	</head>
	
	<body>
		<div class="container">
			<div class="row">
				The riddle will be displayed here
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					<form role="form">
						<div class="form-group">
							<label for="answer">${i18n.answer}</label>
							<input type="text" class="form-control" id="answer" name="obj.answer" placeholder="${i18n.type.your.answer}">
						</div>
						<button type="submit" class="btn btn-lg btn-block btn-success">${i18n.btn.answer}</button>				
					</form>
				</div>
			</div>
		</div>
		<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
	</body>
</html>
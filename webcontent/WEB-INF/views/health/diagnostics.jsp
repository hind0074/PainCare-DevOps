<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/WEB-INF/comps/dashboard/header.jsp" %>

<div class="container mt-3">
	<h1 class="mb-3">Diagnostics History</h1>
    <div class="row">
	    <c:forEach var="item" items="${list}" varStatus="loop">
	    	<div class="col-4 p-3">
   		        <div class="profile-latest-score p-3 d-flex align-items-center rounded border bg-white shadow-sm <c:if test='${item.calcScore() < 50}'>success</c:if>">
	                 <i class="fas fa-exclamation-triangle fa-2x mr-3"></i>
	                 <div class="flex-grow-1">
	                     <h6>${item.getDate()}</h6>
	                     <p>${item.calcResult()}</p>
	                 </div>
	                 <bold class="mx-3 text-center">${item.calcResult()} <br /> ${Math.round(item.calcScore())} <span style="color: #777"> / 100 </span></bold>
	             </div>
	    	</div>

		</c:forEach>
    </div>
</div>

<%@include file="/WEB-INF/comps/dashboard/footer.jsp" %>
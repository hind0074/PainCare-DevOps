<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="activePage" value="dashboard" />

<%@include file="/WEB-INF/comps/dashboard/header.jsp" %>

<div class="container mt-3">
    <div class="row">
        <a href="diagnostic" class="dashboard-action card col p-3 m-3 border rounded cursor-pointer shadow-sm">
            <i class="fas fa-stethoscope fa-3x mb-3"></i>
            <h6>Diagnostic test</h6>
            <p>Discover your potential endometriosis diagnostic</p>
        </a>
        <a href="track_pain" class="dashboard-action card col p-3 m-3 border rounded cursor-pointer shadow-sm">
            <i class="fas fa-chart-line fa-3x mb-3"></i>
            <h6>Track pain</h6>
            <p>Explore your pain journey</p>
        </a>
        <a href="manage_blog" class="dashboard-action card col p-3 m-3 border rounded cursor-pointer shadow-sm">
            <i class="far fa-newspaper fa-3x mb-3"></i>
            <h6>Manage blogs</h6>
            <p>Expand your knowlege by browsing some health blogs</p>
        </a>
    </div>
    <div class="row mb-3">
        <div class="col">
            <c:if test="${not empty diagnosticBean}">
                <div class="profile-latest-score d-flex align-items-center rounded border p-3 bg-white shadow-sm <c:if test='${diagnosticBean.calcScore() < 50}'>success</c:if>">
                    <i class="fas fa-exclamation-triangle fa-2x mr-3"></i>
                    <div class="flex-grow-1">
                        <h6>Last score</h6>
                        <p style="font-size:.9em">Update your score regulary and browse your <a href="diagnostics">history</a>.</p>
                    </div>
                    <bold class="mx-3 text-center">${diagnosticBean.calcResult()} <br /> ${Math.round(diagnosticBean.calcScore())} <span style="color: #777"> / 100 </span></bold>
                </div>
            </c:if> 
            <div class="card shadow-sm mt-3">
                <canvas height="460" class="mt-3" id="pain-level-chart"></canvas>
            </div>
        </div>
        <div class="col">
            <div class="row mb-3">
                <div class="col-6">
                    <div class="card shadow-sm p-3">
                        <h6 class="text-center">Pain Locations</h6>
                        <canvas id="locations-chart"></canvas>
                    </div>
                </div>
                <div class="col-6">
                    <div class="card shadow-sm p-3">
                        <h6 class="text-center">Symptoms</h6>
                        <canvas id="symptoms-chart"></canvas>
                    </div>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-6">
                    <div class="card shadow-sm p-3">
                        <h6 class="text-center">What Makes Pain Worse</h6>
                        <canvas id="worse-pain-chart"></canvas>
                    </div>
                </div>
                <div class="col-6">
                    <div class="card shadow-sm p-3">
                        <h6 class="text-center">Feelings</h6>
                        <canvas id="feelings-chart"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="assets/js/chart.umd.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.0.0"></script>


<script>
	Chart.register(ChartDataLabels)

    // pain evolution
    const datapoints = JSON.parse(`${painArray}`).reverse();
    const dates = JSON.parse(`${painDatesArray}`).reverse();

    // locations
    const locationsObject = JSON.parse(`${locationsObject}`);

    // symptoms
    const symptomsObject = JSON.parse(`${symptomsObject}`);

    // pain worse
    const worsePainObject = JSON.parse(`${worsePainObject}`);

    // symptoms
    const feelingsObject = JSON.parse(`${feelingsObject}`);
</script>

<script src="assets/js/profile.js"></script>

<%@include file="/WEB-INF/comps/dashboard/footer.jsp" %>
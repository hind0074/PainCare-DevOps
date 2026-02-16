<%@include file="/WEB-INF/comps/header.jsp" %>

<div class="dashboard-cnt d-flex">
    <side class="dashboard-side-bar bg-white">
        <div class="user-info d-flex flex-column h-100">
            <div class="user-avatar text-center border-bottom  p-3">
                <c:if test="${not empty userBean.getAvatar()}">
                    <img style="width: 100px; height: 100px; object-fit: cover; border-radius: 50%;" src="${userBean.getAvatar()}" class="logo" alt="Logo">
                </c:if>
                <c:if test="${empty userBean.getAvatar()}">
                    <img style="width: 100px; height: 100px; object-fit: cover; border-radius: 50%;" src="assets/images/logo.png" class="logo" alt="Logo">
                </c:if>
                <p>${userBean.getName()}</p>
                <a href="update_profile">Update Profile</a>
            </div>
            <div class="side-bar-content flex-grow-1 d-flex justify-content-center mt-3 pt-3">
                <ul class="list-unstyled side-bar-links" style="font-family: 'Poppins', sans-serif;">
                    <li class="m-3">
                        <a href="profile" <c:if test="${activePage eq 'dashboard'}">class="active" style="font-weight: 600;"</c:if> >
                            <i class="fas fa-home mr-3"></i> Dashboard
                        </a>
                    </li>
                    <li class="m-3">
                        <a href="diagnostic" <c:if test="${activePage eq 'diagnostic'}">class="active" style="font-weight: 600;"</c:if> href="#">
                            <i class="fas fa-stethoscope mr-3"></i> Diagnostic Test
                        </a>
                    </li>
                    <li class="m-3">
                        <a href="track_pain" <c:if test="${activePage eq 'pain_track'}">class="active" style="font-weight: 600;"</c:if> href="#">
                            <i class="fas fa-chart-line mr-3"></i> Pain Track
                        </a>
                    </li>
                    <li class="m-3">
                        <a href="manage_blog" <c:if test="${activePage eq 'blogs'}">class="active" style="font-weight: 600;"</c:if> href="#">
                            <i class="far fa-newspaper mr-3"></i> Manage Blogs
                        </a>
                    </li>
                </ul>
            </div>
            <div class="side-bar-footer text-center d-flex flex-column align-items-center">
                <img width="100" height="100" src="assets/images/logo-full.png" class="logo m-3" alt="Logo">
                <a href="home" class="theme-btn m-3">
                    <i class="fas fa-sign-out-alt fa-rotate-180"></i> Back Home
                </a>
            </div>
        </div>
    </side>
    <main class="dashboard-content flex-grow-1">

        
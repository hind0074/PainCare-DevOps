<!-- main header -->
<header class="main-header">
    <div class="outer-box clearfix">
        <div class="logo-box pull-left">
            <figure class="logo"><a href="home"><img style="height: 60px" src="assets/images/logo-full.png" alt=""></a></figure>
        </div>
        <div class="menu-area pull-right">
            <!--Mobile Navigation Toggler-->
            <div class="mobile-nav-toggler">
                <i class="icon-bar"></i>
                <i class="icon-bar"></i>
                <i class="icon-bar"></i>
            </div>
            <nav class="main-menu navbar-expand-md navbar-light">
				<div class="collapse navbar-collapse show clearfix d-flex" id="navbarSupportedContent">
					<ul class="navigation scroll-nav clearfix">
						<li <c:if test="${empty activePage}">class="current"</c:if> ><a href="home#home">Home</a></li>

                        <c:if test="${empty activePage}">
                            <li><a href="#about">About</a></li>
                            <li><a href="#symptoms">Symptoms</a></li>
                            <li><a href="#prevention">Prevention</a></li>
                        </c:if>
						
                        

						<li <c:if test="${activePage eq 'faq'}">class="current"</c:if>><a href="faq#faq">FAQ</a></li>
                        <li <c:if test="${activePage eq 'articles'}">class="current"</c:if>><a href="articles#articles">Articles</a></li>
						<li <c:if test="${activePage eq 'blogs'}">class="current"</c:if>><a href="blogs#blogs">Blogs</a></li>
                        <li>
                            <c:if test="${not empty userBean}">
                                <div>
                                    <button class=" btn dropdown-toggle p-0" data-toggle="dropdown" style="outline: none !important;box-shadow: none !important;"
                                        type="button" id="dropdownMenu2" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false">
                                        <div class="avatar" style="display: inline-block;">
                                            <c:if test="${not empty userBean.getAvatar()}">
                                                <img class="navbar-avatar" width="50" height="50" src="${userBean.getAvatar()}" >
                                            </c:if>
                                            <c:if test="${empty userBean.getAvatar()}">
                                                <img class="p-0 navbar-avatar" width="50" height="50" src="assets/images/logo.png" >
                                            </c:if>
                                           
                                    </button>
                                                                        
                                    
                                    <div style="min-width: 15vw;" class="dropdown-menu dropdown-menu-right">
                                        <a href="profile" class="dropdown-item" type="button">Profile</a>
                                        <a href="manage_blog" class="dropdown-item" type="button">Manage Blogs</a>
                                        <div class="dropdown-divider"></div>
                                        <a href="logout" class="dropdown-item" type="button">Logout</a>
                                    </div>
                                </div>
                            </c:if>
                            
                    
                            <c:if test="${empty userBean}">
                                <a href="login" class="btn-box p-0">
                                    <button class="btn theme-btn">Login</button>
                                </a>
                            </c:if>
                        </li>
					</ul>
                    
                </div>
            </nav>
		</div>
    </div>

    <!--sticky Header-->
    <div class="sticky-header">
        <div class="outer-box clearfix">
            <div class="logo-box pull-left">
                <figure class="logo"><a href="home"><img class="p-0" style="height: 60px" src="assets/images/logo-full.png"
											class="logo" alt="Logo"></a></figure>
            </div>
            <div class="menu-area pull-right">
                <nav class="main-menu clearfix">
                    <!--Keep This Empty / Menu will come through Javascript-->
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- main-header end -->

    <!-- Mobile Menu  -->
    <div class="mobile-menu">
        <div class="menu-backdrop"></div>
        <div class="close-btn"><i class="fas fa-times"></i></div>
        
        <nav class="menu-box">
            <div class="menu-outer"><!--Here Menu Will Come Automatically Via Javascript / Same Menu as in Header--></div>
            <div class="contact-info">
                <h4>Contact Info</h4>
                <ul>
                    <li>Chicago 12, Melborne City, USA</li>
                    <li><a href="tel:+8801682648101">+88 01682648101</a></li>
                    <li><a href="mailto:info@example.com">info@example.com</a></li>
                </ul>
            </div>
            <div class="social-links">
                <ul class="clearfix">
                    <li><a href="home"><span class="fab fa-twitter"></span></a></li>
                    <li><a href="home"><span class="fab fa-facebook-square"></span></a></li>
                    <li><a href="home"><span class="fab fa-pinterest-p"></span></a></li>
                    <li><a href="home"><span class="fab fa-instagram"></span></a></li>
                    <li><a href="home"><span class="fab fa-youtube"></span></a></li>
                </ul>
            </div>
        </nav>
    </div>
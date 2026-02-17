<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/comps/header.jsp"%>

<c:set var="activePage" value="articles" />

<%@include file="/WEB-INF/comps/navbar.jsp"%>

<style>
	.blog-image {
		height: 300px !important;
	}
</style>

<section class="page-title centred">
    <div class="auto-container">
        <div class="content-box">
            <h1>Articles</h1>
            <ul class="bread-crumb clearfix">
                <li><a href="home">Home</a></li>
                <li>Articles</li>
            </ul>
        </div>
    </div>
</section>

<div class="anim-icon">
	<div class="icon icon-1 rotate-me"
		style="background-image: url(assets/images/icons/anim-icon-1.png);"></div>
	<div class="icon icon-2 rotate-me"
		style="background-image: url(assets/images/icons/anim-icon-2.png);"></div>
	<div class="icon icon-3 rotate-me"
		style="background-image: url(assets/images/icons/anim-icon-3.png);"></div>
</div>
<div class="shape-layer" style="background-image: url(assets/images/shape/shape-1.png);"></div>
<div class="centred container row mx-auto pb-3">
	<div class="col-md-6 col-sm-12 news-block py-3">
		<div class="news-block-one wow fadeInUp animated animated h-100"
			data-wow-delay="00ms" data-wow-duration="1500ms">
			<div class="inner-box h-100">
				<figure class="image-box blog-image">
					<a href="article?num=1"> <img
						src="assets\images\articles\le-reve.72442.jpg" />
					</a>
				</figure>
				<div class="lower-content">

					<h3>
						<a href="article?num=1">Surgical treatment of endometriosis</a>
					</h3>
					<p class="art_desc">Guidelines favor laparoscopic surgery for
						endometriosis due to its benefits. While laparotomy is equally
						effective, robotic surgery, despite comparable outcomes, is less
						mentioned. The choice depends on individual factors and surgeon
						expertise.</p>
					<div class="btn-box">
						<a href="article?num=1">Read more</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6 col-sm-12 news-block py-3">
		<div class="news-block-one wow fadeInUp animated animated h-100"
			data-wow-delay="00ms" data-wow-duration="1500ms">
			<div class="inner-box h-100">
				<figure class="image-box blog-image">
					<a href="article?num=2"> <img
						src="assets\images\articles\article2.jpg" />
					</a>
				</figure>
				<div class="lower-content">
					<h3>
						<a href="article?num=2">Why does endometriosis cause so much
							pain?</a>
					</h3>
					<p class="art_desc">Endometriosis manifests with symptoms like
						extreme pain, painful sex, and gastrointestinal disruptions. The
						condition occurs when tissue similar to the uterine lining grows
						outside the uterus, causing intense pain during periods and
						between them.</p>
					<div class="btn-box">
						<a href="article?num=2">Read more</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6 col-sm-12 news-block py-3">
		<div class="news-block-one wow fadeInUp animated animated h-100"
			data-wow-delay="00ms" data-wow-duration="1500ms">
			<div class="inner-box h-100">
				<figure class="image-box blog-image">
					<a href="article?num=3"> <img
						src="assets\images\articles\article3.png" />
					</a>
				</figure>
				<div class="lower-content">

					<h3>
						<a href="article?num=3">They Call It a Womens Disease. She Wants to Redefine It.</a>
					</h3>
					<p class="art_desc">Linda Griffith's M.I.T. Center for
						Gynepathology Research, distinct from typical women's disease
						labs, lacks overt feminine symbols. Located within the biological
						engineering building, it is identified by the letters CGR in red
						and black, representing a unique and focused research approach.</p>
					<div class="btn-box">
						<a href="article?num=3">Read more</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6 col-sm-12 news-block py-3">
		<div class="news-block-one wow fadeInUp animated animated h-100"
			data-wow-delay="00ms" data-wow-duration="1500ms">
			<div class="inner-box h-100">
				<figure class="image-box blog-image">
					<a href="article?num=4"> <img
						src="assets\images\articles\article4.jpg" />
					</a>
				</figure>
				<div class="lower-content">

					<h3>
						<a href="article?num=4">Researchers optimistic about potential
							new treatment for endometriosis</a>
					</h3>
					<p class="art_desc">Clinical trial in Edinburgh and London
						tests dichloroacetate for endometriosis, potentially the first
						non-hormonal, non-surgical treatment in 40 years. Promising early
						results for better pain management.</p>
					<div class="btn-box">
						<a href="article?num=4">Read more</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<footer class="main-footer">
	<div class="shape-layer"
		style="background-image: url(assets/images/shape/shape-3.png);"></div>
	<div class="anim-icon">
		<div class="icon icon-1 rotate-me"
			style="background-image: url(assets/images/);"></div>
		<div class="icon icon-2 rotate-me"
			style="background-image: url(assets/images/);"></div>
		<div class="icon icon-3 rotate-me"
			style="background-image: url(assets/images/);"></div>
	</div>
	<div class="footer-top">
		<div class="auto-container">
			<div class="widget-section">
				<div class="row clearfix">
					<div class="col-lg-3 col-md-6 col-sm-12 footer-column">
						<div class="logo-widget footer-widget">
							<figure class="footer-logo">
								<a href="index.html"><img style="height: 60px"
									src="assets/images/logo-full.png" alt=""></a>
							</figure>
							<div class="text">
								<p>The best way to prevent and slow down transmission is be
									well informed about the Endometriosis, the disease it causes
									and how it spreads.</p>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 col-sm-12 footer-column">
						<div class="links-widget service footer-widget">
							<div class="widget-title">
								<h3>Services</h3>
							</div>
							<div class="widget-content">
								<ul class="list clearfix">
									<li><a href="index.html">About Us</a></li>
									<li><a href="index.html">Symptoms</a></li>
									<li><a href="index.html">Prevention</a></li>
									<li><a href="index.html">Our Doctors</a></li>
									<li><a href="index.html">Our Blog</a></li>
									<li><a href="index.html">Contact Us</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 col-sm-12 footer-column">
						<div class="links-widget region footer-widget">
							<div class="widget-title">
								<h3>Regions</h3>
							</div>
							<div class="widget-content">
								<ul class="list clearfix">
									<li><a href="index.html">America</a></li>
									<li><a href="index.html">Africa</a></li>
									<li><a href="index.html">South East Asia</a></li>
									<li><a href="index.html">Europe</a></li>
									<li><a href="index.html">Australia</a></li>
									<li><a href="index.html">Westenr Pacific</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 col-sm-12 footer-column">
						<div class="contact-widget footer-widget">
							<div class="widget-title">
								<h3>Contacts</h3>
							</div>
							<ul class="info-list clearfix">
								<li><i class="fas fa-map-marker-alt"></i>Avenue Mohamed Ben
									Abdellah, Avenue Regragui, 10112 Rabat,Ensias</li>
								<li><i class="fas fa-microphone"></i><a
									href="tel:+212 619-214446">+212 619-214446</a></li>
								<li><i class="fas fa-envelope"></i><a
									href="mailto:info@example.com">info@example.com</a></li>
							</ul>
							<ul class="social-links clearfix">
								<li><a href="index.html"><i class="fab fa-facebook-f"></i></a></li>
								<li><a href="index.html"><i class="fab fa-twitter"></i></a></li>
								<li><a href="index.html"><i class="fab fa-vimeo-v"></i></a></li>
								<li><a href="index.html"><i class="fab fa-linkedin-in"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer-bottom">
		<div class="auto-container">
			<div class="bottom-inner clearfix">
				<div class="copyright pull-left">
					<p>
						<a href="index.html">PainCare</a> &copy; Project done by a groupe
						of ENSIAS students IDSIT second year
					</p>
				</div>
				<ul class="footer-nav pull-right">
					<li><a href="index.html">Terms of Service</a></li>
					<li><a href="index.html">Privacy Policy</a></li>
				</ul>
			</div>
		</div>
	</div>
</footer>

<%@include file="/WEB-INF/comps/footer.jsp"%>

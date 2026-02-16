<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/comps/header.jsp"%>

<%@include file="/WEB-INF/comps/navbar.jsp"%>

<style>
.icn {
	height: 90px;
	width: 90px
}
</style>
<!-- banner-section -->
<section class="banner-section" id="home">
	<div class="anim-icon">
		<div class="icon icon-1 rotate-me"
			style="background-image: url(assets/images/icons/anim-icon-1.png);"></div>
		<div class="icon icon-2 rotate-me"
			style="background-image: url(assets/images/icons/anim-icon-2.png);"></div>
		<div class="icon icon-3 rotate-me"
			style="background-image: url(assets/images/icons/anim-icon-3.png);"></div>
	</div>
	<div class="shape-layer"
		style="background-image: url(assets/images/shape/shape-1.png);"></div>
	<div class="banner-carousel owl-theme owl-carousel owl-dots-none">
		<div class="slide-item">
			<div class="auto-container">
				<div class="row align-items-center clearfix">
					<div class="col-lg-6 col-md-6 col-sm-12 content-column">
						<div class="content-box">
							<h6>Understanding Endometriosis</h6>

							<h1>Track Your Journey, Seek Professional Guidance</h1>

							<p>Unite in strength against endometriosis. Track your
								symptoms and seek professional guidance. Let's break the
								silence, raise awareness, and foster healing through hope.</p>

							<div class="btn-box">
								<a href="track_pain">Track pain</a>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 image-column">
						<figure class="image-box">
							<img src="assets/images/endromesis2.jpg" alt="">
						</figure>
					</div>
				</div>
			</div>
		</div>
		<div class="slide-item">
			<div class="auto-container">
				<div class="row align-items-center clearfix">
					<div class="col-lg-6 col-md-6 col-sm-12 content-column">
						<div class="content-box">
							<h6>Endometriosis</h6>
							<h1>A silent battle, A relentless strength.</h1>
							<p>Together, we raise our voices and heal with hope.</p>
							<div class="btn-box">
								<a href="diagnostic">Perform a diagnosys</a>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 image-column">
						<figure class="image-box">
							<img src="assets/images/endromesis1.jpg" alt="">
						</figure>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- banner-section end -->


<!-- about-section -->
<section class="about-section bg-color-1" id="about">
	<div class="anim-icon">
		<div class="icon icon-1 rotate-me"
			style="background-image: url(assets/images/icons/anim-icon-4.png);"></div>
	</div>
	<div class="auto-container">
		<div class="sec-title centred">
			<h6>About Endometriosis</h6>
			<h2>
				Endometriosis <br />
			</h2>
		</div>
		<div class="row clearfix">
			<div class="col-lg-6 col-md-12 col-sm-12 image-column">
				<figure class="image-box wow slideInLeft animated animated"
					data-wow-delay="00ms" data-wow-duration="1500ms">
					<img src="assets\images\Endometriosis3.jpg" alt="">
				</figure>
			</div>
			<div class="col-lg-6 col-md-12 col-sm-12 content-column">
				<div class="content-box">
					<div class="tabs-box">
						<div class="tab-btn-box centred">
							<ul class="tab-btns tab-buttons clearfix">
								<li class="tab-btn active-btn" data-tab="#tab-1">Overview</li>
								<li class="tab-btn" data-tab="#tab-2">Treatment</li>
							</ul>
						</div>
						<div class="tabs-content">
							<div class="tab active-tab" id="tab-1">
								<div class="text">
									<p>Endometriosis is a condition where tissue similar to the
										lining of the uterus grows outside it. This can cause pain,
										inflammation, and affect nearby organs. Common symptoms
										include pelvic pain, cramping, and irregular bleeding. It may
										also impact fertility. Medical attention is needed for
										management, and sometimes surgery is required to alleviate
										symptoms.</p>
									<ul class="list-item clearfix">
										<li>Apply a heating pad for pain relief.</li>
										<li>Opt for an anti-inflammatory diet.</li>
										<li>Embrace regular, moderate exercise.</li>
									</ul>
								</div>
							</div>
							<div class="tab" id="tab-2">
								<div class="text">
									<p>Endometriosis treatment combines medications for pain
										relief and hormonal management with minimally invasive surgery
										to remove abnormal tissue. This tailored approach aims to
										alleviate symptoms, with the choice of treatment depending on
										individual needs and goals. Regular monitoring and
										collaboration with healthcare professionals are key components
										for effective management.</p>
									<ul class="list-item clearfix">
										<li>Manage symptoms with pain relievers, hormonal
											treatments, or contraceptives.</li>
										<li>Consider minimally invasive surgery to remove
											endometrial tissue and adhesions.</li>
										<li>Explore hormonal interventions to suppress estrogen
											and alleviate symptoms.</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- about-section end -->


<!-- funfact-section -->
<section class="funfact-section">
	<div class="auto-container">
		<div class="inner-container">
			<div class="row clearfix">
				<div class="col-lg-4 col-md-6 col-sm-12 counter-block">
					<div class="counter-block-one">
						<div class="inner-box">
							<figure class="icon-box">
								<img class="icn" src="assets\images\icons\R.png" alt="">
							</figure>
							<div class="count-outer count-box">
								<span class="count-text" data-speed="1500" data-stop="924628">0</span>
							</div>
							<p>Number affected</p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12 counter-block">
					<div class="counter-block-one">
						<div class="inner-box">
							<figure class="icon-box">
								<img class="icn" src="assets/images/icons/blue.png" alt="">
							</figure>
							<div class="count-outer count-box">
								<span class="count-text" data-speed="1500" data-stop="337276">0</span>
							</div>
							<p>Recovered Cases</p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12 counter-block">
					<div class="counter-block-one">
						<div class="inner-box">
							<figure class="icon-box">
								<img class="icn" src="assets/images/icons/green.png" alt="">
							</figure>
							<div class="count-outer count-box ">
								<span class="count-text" data-speed="1500" data-stop="89575">0</span>
							</div>
							<p>Total Deaths</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- funfact-section end -->


<!-- symptoms-section -->
<section class="symptoms-section" id="symptoms">
	<div class="auto-container">
		<div class="row clearfix">
			<div class="col-lg-6 col-md-12 col-sm-12 content-column">
				<div class="content-box">
					<div class="sec-title">
						<h6>Main Symptoms</h6>
						<h2>What Are The Main Symptoms?</h2>
					</div>
					<div class="text">
						<p>Endometriosis is a condition where tissue similar to the
							lining of the uterus grows outside the uterus. The symptoms of
							endometriosis can vary widely among individuals, and some may not
							experience noticeable symptoms.</p>
					</div>
					<div class="inner-box clearfix">
						<div class="single-column">
							<p>Common Symptoms Include:</p>
							<ul class="list-item clearfix">
								<li>Pelvic Pain</li>
								<li>Dysmenorrhea (Menstrual Cramps)</li>
								<li>Painful Intercourse (Dyspareunia)</li>
							</ul>
						</div>
						<div class="single-column">
							<p>Other Symptoms Include:</p>
							<ul class="list-item clearfix">
								<li>Irregular Menstrual Bleeding</li>
								<li>Infertility</li>
								<li>Fatigue</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-12 col-sm-12 image-column">
				<figure class="image-box wow slideInRight animated animated"
					data-wow-delay="00ms" data-wow-duration="1500ms">
					<img src="assets\images\symptoms.png" alt="">
				</figure>
			</div>
		</div>
	</div>
</section>
<!-- symptoms-section end -->


<!-- protect-section -->
<section class="protect-section centred" id="prevention">
	<div class="shape-layer"
		style="background-image: url(assets/images/shape/shape-2.png);"></div>
	<div class="anim-icon">
		<div class="icon icon-1 rotate-me"
			style="background-image: url(assets/images/icons/anim-icon-1.png);"></div>
		<div class="icon icon-2 rotate-me"
			style="background-image: url(assets/images/icons/anim-icon-3.png);"></div>
	</div>
	<div class="auto-container">
		<div class="sec-title">
			<h6>How To Protect</h6>
			<h2>
				Take Steps To Protect <br />Yourself
			</h2>
		</div>
		<div class="row clearfix">
			<div class="col-lg-4 col-md-6 col-sm-12 single-column">
				<div class="single-item wow fadeInUp animated animated"
					data-wow-delay="300ms" data-wow-duration="1500ms">
					<div class="inner-box">
						<figure class="image-box">
							<img src="assets/images/manage_menstrual.png" alt="">
						</figure>
						<h3>Manage Your Menstrual Health</h3>
						<p>Discuss birth control options with your healthcare provider
							to find the one that's most suitable for you.</p>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-6 col-sm-12 single-column">
				<div class="single-item wow fadeInUp animated animated"
					data-wow-delay="300ms" data-wow-duration="1500ms">
					<div class="inner-box">
						<figure class="image-box">
							<img src="assets/images/prevention2.jpg" alt="">
						</figure>
						<h3>Medical Check-ups</h3>
						<p>Regular gynecological check-ups can help diagnose
							endometriosis early and enable healthcare providers to develop a
							treatment plan .</p>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-6 col-sm-12 single-column">
				<div class="single-item wow fadeInUp animated animated"
					data-wow-delay="300ms" data-wow-duration="1500ms">
					<div class="inner-box">
						<figure class="image-box">
							<img src="assets/images/prevent3.jpg" alt="">
						</figure>
						<h3>Lead a Healthy Lifestyle</h3>
						<p>Maintain a healthy body weight. Some studies suggest that
							there may be a link between obesity and a higher risk of
							endometriosis.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- protect-section end -->


<!-- faq-section -->
<section class="faq-section" id="faq">
	<div class="anim-icon">
		<div class="icon icon-1 rotate-me"
			style="background-image: url(assets/images);"></div>
		<div class="icon icon-2 rotate-me"
			style="background-image: url(assets/imagesg);"></div>
		<div class="icon icon-3 rotate-me"
			style="background-image: url(assets/images/);"></div>
	</div>
	<div class="auto-container">
		<div class="row clearfix">
			<div class="col-lg-6 col-md-12 col-sm-12 content-column">
				<div class="content-box">
					<div class="sec-title">
						<h6>Freequently Asked Question</h6>
						<h2>Common Question & Answer</h2>
					</div>
					<div class="text">
						<p>WHO is continuously monitoring and responding to this
							outbreak. This Q&A will be updated as more is known about
							Endometriosis, how it diagnosis and how can be cured. For more
							information, check back the Read More section.</p>
					</div>
					<div class="btn-box">
						<a href="faq" class="theme-btn">Read More</a>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-12 col-sm-12 faq-column">
				<ul class="accordion-box">
					<li class="accordion block active-block">
						<div class="acc-btn active">
							<h4>
								<span>Q.</span>How is endometriosis diagnosed?
							</h4>
						</div>
						<div class="acc-content current">
							<div class="text">
								<p>Endometriosis is typically diagnosed through a surgical
									procedure called laparoscopy, in which a small camera is
									inserted into the abdomen to visualize and biopsy the abnormal
									tissue. Some healthcare providers may make a clinical diagnosis
									based on symptoms and physical examination.</p>
							</div>
						</div>
					</li>
					<li class="accordion block">
						<div class="acc-btn">
							<h4>
								<span>Q.</span> What causes endometriosis?
							</h4>
						</div>
						<div class="acc-content">
							<div class="text">
								<p>The exact cause of endometriosis is not fully understood,
									but it is likely due to a combination of genetic, hormonal, and
									environmental factors. Retrograde menstruation, where menstrual
									blood flows back into the pelvis, is one possible explanation.</p>
							</div>
						</div>
					</li>
					<li class="accordion block">
						<div class="acc-btn">
							<h4>
								<span>Q.</span>there is a link between endometriosis and certain
								cancers?
							</h4>
						</div>
						<div class="acc-content">
							<div class="text">
								<p>There is some evidence of a slightly increased risk of
									certain ovarian cancers in individuals with endometriosis, but
									the overall risk is still relatively low. Regular medical
									check-ups and discussions with healthcare providers can help
									manage any potential concerns.</p>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</section>
<!-- faq-section end -->



<!-- article-section -->
<section class="news-section" id="articles">
	<div class="auto-container">
		<div class="row clearfix">
			<div class="col-lg-6 col-md-12 col-sm-12 content-column">
				<div class="content-box">
					<div class="sec-title">
						<h6>Articles</h6>
						<h2>Get the detailed info on diffrenet endometriosis subjects</h2>
					</div>
					<div class="text">
						<p>Discover a trove of articles on endometriosis insights,
							treatments, and personal stories. Gain valuable knowledge and
							support.</p>
					</div>
					<div class="btn-box my-3">
						<a href="articles" class="theme-btn ">See Articles</a>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-12 col-sm-12 faq-column">
				<img src="assets\images\articlehome.png" />
			</div>
		</div>
	</div>
</section>
<!-- article-section end -->


<!-- news-section -->
<section class="news-section" id="blogs">
	<div class="anim-icon">
		<div class="icon icon-1 rotate-me"
			style="background-image: url(assets/images/);"></div>
		<div class="icon icon-2 rotate-me"
			style="background-image: url(assets/images/);"></div>
		<div class="icon icon-3 rotate-me"
			style="background-image: url(assets/images/);"></div>
	</div>
	<div class="auto-container">
		<div class="sec-title centred">
			<h6>Our Blogs</h6>
			<h2>
				Stay Updated with Our <br />News Feed.
			</h2>
		</div>
		<div class="row clearfix">
			<c:forEach var="blog" items="${listOfBlogs}">
				<div class="col-lg-4 col-md-6 col-sm-12 news-block  container">
					<div class="news-block-one wow fadeInUp animated animated"
						data-wow-delay="00ms" data-wow-duration="1500ms">
						<div class="inner-box">
							<figure class="image-box blog-image">
								<a href="blog?id=${blog.getID()}"> <img
									src="${blog.getImage()}" />
								</a>
							</figure>
							<div class="lower-content">

								<h3>
									<a href="blog-details.html">${blog.getTitle()}</a>
								</h3>
								<ul class="post-info clearfix">
									<li><img src="${blog.getUserImage()}" />${blog.getUserName()}
									</li>
									<li>${blog.getDate().toString()}</li>

								</ul>
								<p></p>
								<div class="btn-box">
									<a href="Blog?id=${blog.getID()}">See Details</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="btn-box text-center m-3">
			<a href="blogs" class="theme-btn m-3">Read More Blogs</a>
		</div>
	</div>
</section>
<!-- news-section end -->


<!-- cta-section -->

<section class="cta-section">


	<div class="auto-container">

		<div class="inner-container">

			<div class="row clearfix">
				<div class="col-xl-6 col-lg-12 col-md-12 text-column">
					<div class="text">
						<div class="row clearfix">
							<div class="col-xl-2 col-lg-2 col-md-2 ">
								<img src="assets/images/icons/smartphone-call.png" alt=""
									style="size: 10%;">
							</div>
							<h3 class="col-xl-10 col-lg-10 col-md-10 text-column">Install
								the PainCare Android App</h3>
						</div>
					</div>
				</div>
				<div class="col-xl-6 col-lg-12 col-md-12 btn-column">
					<div class="btn-box clearfix">

						<a href="index.html" class="theme-btn"> Download App </a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- cta-section end -->


<!-- main-footer -->
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
<!-- main-footer end -->


<%@include file="/WEB-INF/comps/footer.jsp"%>
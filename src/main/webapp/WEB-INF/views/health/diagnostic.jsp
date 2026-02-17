<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="activePage" value="diagnostic" />

<%@include file="/WEB-INF/comps/dashboard/header.jsp" %>

<div style="min-height: 100%;" class="container d-flex flex-column justify-content-center align-items-center protect-section p-3 <c:if test="${empty diagnosticBean}">h-100</c:if>">
    <c:if test="${empty diagnosticBean}">
        <div id="diagnstic-notaion" class="active d-flex flex-column align-items-center justify-content-center">
            <img width="500" src="assets/images/diagnostic.png" />
            <p class="my-3">Do you suffer from painful period ? Take this questionnaire to find out your risk for endometriosis.</p>
            <div class="more-btn mt-3">
                <a href="javascript:void;">GO</a>
            </div>
        </div>
        <form class="d-flex flex-column h-100 w-100" id="diagnostic-form" action="" method="POST">
            <div id="diagnostic-panels" class="flex-grow-1 d-flex justify-content-center align-items-center">
                <c:forEach var="item" items="${questionsBank}" varStatus="loop">
                    <div data-type="${item[0]}" class="diagnostic-panel <c:if test="${loop.index != 0}">d-none</c:if>">
                        <div class="mb-3">
                            <h4>${item[1]}</h4>
                        </div>
                        <c:choose>
                            <c:when test="${item[0] eq 'radio'}">
                                <c:forEach var="subItem" items="${item[2]}" varStatus="loop2">
                                    <div class="m-3">
                                        <input type="radio" name="answer-${loop.index}" <c:if test="${loop.index == 0}">required</c:if> data-value="${loop2.index}" id="Q${loop.index}.${loop2.index}">
                                        <lable for="Q${loop.index}.${loop2.index}">${subItem}</lable>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:when test="${item[0] eq 'checkbox'}">
                                <c:forEach var="subItem" items="${item[2]}" varStatus="loop2">
                                    <div class="m-3">
                                        <input type="checkbox" name="answer-${loop.index}-${loop2.index}" data-value="${loop2.index}" id="Q${loop.index}.${loop2.index}">
                                        <lable for="Q${loop.index}.${loop2.index}">${subItem}</lable>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:when test="${item[0] eq 'number'}">
                                <c:forEach var="subItem" items="${item[2]}" varStatus="loop2">
                                    <div class="m-3">
                                        <input class="form-control" placeholder="${subItem}" type="number" name="answer-${loop.index}" <c:if test="${loop.index == 0}">required</c:if> data-value="${loop2.index}" id="Q${loop.index}.${loop2.index}">
                                    </div>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </div>
                </c:forEach>
            </div>
            <input type="hidden" name="answers" id="answers" />
            <div class="d-flex justify-content-end w-100 p-3">
                <button type="button" disabled="true" id="prev-btn" class="theme-btn mr-3">Prev</button>
                <button id="submit-btn" class="theme-btn">Next</button>
            </div>
        </form>
    </c:if>
    <c:if test="${not empty diagnosticBean}">
        <div class="mb-3">
            <p>Risk Level : <strong>${diagnosticBean.calcResult()} (${Math.round(diagnosticBean.calcScore())})</strong></p>
        </div>
        <c:forEach var="item" items="${questionsBank}" varStatus="loop">
            <div class="mb-3">
                <p><strong>${item[1]}</strong></p>
            </div>
            <div class="mb-3">
                <c:choose>
                    <c:when test="${item[0] eq 'radio'}">
                        <p>${item[2][diagnosticBean.getAnswers().get(loop.index).get(0)]}</p>
                    </c:when>
                    <c:when test="${item[0] eq 'checkbox'}">
                        <p>
                            <c:forEach var="answer" items="${diagnosticBean.getAnswers().get(loop.index)}">
                                <span class="m-3">
                                    ${item[2][answer]}
                                </span>
                            </c:forEach>
                        </p>
                    </c:when>
                    <c:when test="${item[0] eq 'number'}">
                        <c:forEach var="answer" items="${diagnosticBean.getAnswers().get(loop.index)}" varStatus="loop2">
                            <p>${item[2][loop2.index]} : ${answer}</p>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </div>
        </c:forEach>
        <div class="more-btn mt-3">
            <a href="profile">Back to dashboard</a>
        </div>
    </c:if>
</div>

<%@include file="/WEB-INF/comps/dashboard/footer.jsp" %>

<script>
    const diagnosticButton = document.querySelector("#diagnstic-notaion a");
    const form = document.getElementById("diagnostic-form");
    const prev = document.getElementById("prev-btn");
    const panels = document.querySelectorAll(".diagnostic-panel");
    const inputs = document.querySelectorAll("#diagnostic-panels input");
    const button = document.getElementById("submit-btn");
    let active_panel = 0;

    diagnosticButton.addEventListener("click", () => {
        const diagnostic = document.getElementById("diagnstic-notaion");

        diagnostic.classList.remove("active");

        window.setTimeout(() => diagnostic.remove(), 250);
    })

    prev.addEventListener("click", () => {
        if(active_panel > 0) {
            panels[active_panel].classList.add("d-none");
            panels[active_panel].querySelectorAll("input[type='radio'], input[type='number']").forEach(input => {
                input.removeAttribute("required");
            });

            panels[active_panel - 1].classList.remove("d-none");
            panels[active_panel - 1].querySelectorAll("input[type='radio'], input[type='number']").forEach(input => {
                input.setAttribute("required", "true");
            });

            active_panel--;

            if(active_panel == 0) prev.setAttribute("disabled", "true");
            button.textContent = "next";
        }
    })

    form.addEventListener("submit", e => {
        e.preventDefault();

        if(active_panel + 1 < panels.length) {
            panels[active_panel].classList.add("d-none");
            panels[active_panel].querySelectorAll("input[type='radio'], input[type='number']").forEach(input => {
                input.removeAttribute("required");
            });

            panels[active_panel + 1].classList.remove("d-none");
            panels[active_panel + 1].querySelectorAll("input[type='radio'], input[type='number']").forEach(input => {
                input.setAttribute("required", "true");
            });

            active_panel++;

            if(active_panel + 1 == panels.length) button.textContent = "sumbit";
            if(active_panel > 0) prev.removeAttribute("disabled");
        } else submitAnswers();
    });

    function submitAnswers() {
        const answers = [];

        panels.forEach(panel => {
            const type = $(panel).data("type");

            switch(type) {
                case "radio": {
                    $(panel).find("input").each(function () {
                        if($(this)[0].checked) answers.push($(this).data("value"));
                    });
                }; break;
                case "number": {
                    const values = [];
                    $(panel).find("input").each(function () {
                        values.push($(this).val());
                    });

                    answers.push(values.join("-"));
                }; break;
                case "checkbox": {
                    const values = [];

                    $(panel).find("input").each(function () {
                        if($(this)[0].checked) values.push($(this).data("value"));
                    });

                    answers.push(values.join("-"));
                }; break;
            }
        });

        const answersString = answers.join(",");

        document.getElementById("answers").value = answersString;

        document.getElementById("diagnostic-form").submit();
    }
</script>
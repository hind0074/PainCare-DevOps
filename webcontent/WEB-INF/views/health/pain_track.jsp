<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="activePage" value="pain_track" />

<%@include file="/WEB-INF/comps/dashboard/header.jsp" %>

<div class="container app-form-cnt" style="padding: 3em 10em">

	<form id="pain-form" action="" method="POST">
        <h1 class="h3 mb-3">Track Pain</h1>
		<div class="mb-3">
			<label for="pain">Pain Level : <span id="pain_label">None (0)</span></label>
			<input name="level" type="range" class="form-control-range" min="0" max="10" value="0" id="pain">
		</div>
        <div class="row">
            <div class="col-6">
                <div class="mb-3 pain-field">
                    <input type="hidden" name="locations" />
                    <p class="mb-3">Pain Locations : </p>
                    <div class="m-3">
                        <input type="checkbox" id="pl1">
                        <label for="pl1">Abdomen</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="pl2">
                        <label for="pl2">Back</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="pl3">
                        <label for="pl3">Chest</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="pl4">
                        <label for="pl4">Head</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="pl5">
                        <label for="pl5">Neck</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="pl6">
                        <label for="pl6">Hips</label>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3 pain-field">
                    <input type="hidden" name="symptoms" />
                    <p class="mb-3">Symptoms : </p>
                    <div class="m-3">
                        <input type="checkbox" id="sym1">
                        <label for="sym1">Cramps</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="sym2">
                        <label for="sym2">Tender breasts</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="sym3">
                        <label for="sym3">Headache</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="sym4">
                        <label for="sym4">Acne</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="sym5">
                        <label for="sym5">Fatigue</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="sym6">
                        <label for="sym6">Bloating</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="sym7">
                        <label for="sym7">Craving</label>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3 pain-field">
                    <input type="hidden" name="worse_pain" />
                    <p class="mb-3">What makes pain worse: </p>
                    <div class="m-3">
                        <input type="checkbox" id="pw1">
                        <label for="pw1">Lack of sleep</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="pw2">
                        <label for="pw2">Sitting</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="pw3">
                        <label for="pw3">Standing</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="pw4">
                        <label for="pw4">Stress</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="pw5">
                        <label for="pw5">Walking</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="pw6">
                        <label for="pw6">Exercise</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="pw7">
                        <label for="pw7">Urination</label>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3 pain-field">
                    <input type="hidden" name="feelings" />
                    <p class="mb-3">Feelings : </p>
                    <div class="m-3">
                        <input type="checkbox" id="fl1">
                        <label for="fl1">Anxious</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="fl2">
                        <label for="fl2">Depressed</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="fl3">
                        <label for="fl3">Dizzy</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="fl4">
                        <label for="fl4">Vomiting</label>
                    </div>
                    <div class="m-3">
                        <input type="checkbox" id="fl5">
                        <label for="fl5">Diarrhea</label>
                    </div>
                </div>
            </div>
        </div>
		
		<button type="submit" class="theme-btn position-fixed" style="bottom: 1em; right: 2em;">Submit</button>
	</form>
	
</div>

<script>
    const form = document.getElementById("pain-form");

    form.addEventListener("submit", () => {
        const painFields = document.querySelectorAll(".pain-field");

        painFields.forEach(painField => {
            const hiddenInput = painField.querySelector("input[type='hidden']");
            const inputs = painField.querySelectorAll("input[type='checkbox']");
            let values = [];

            inputs.forEach((input, index) => {
                if(input.checked) values.push(index);
            });

            hiddenInput.value = values.join(",");
        });
    });

    const input = document.getElementById("pain");
    const label = document.getElementById("pain_label");

    input.addEventListener("change", () => {
        let content = "", classes = "";
        const value = parseInt(input.value);

        if(value == 0) {
            content = "None";
        };

        if(value > 0) {
            console.log(value);
            content = "Mild";
            classes = "text-success";
        };

        if(value > 4) {
            content = "Moderate";
            classes = "text-warning";
        }

        if(value > 6) {
            content = "Very severe";
            classes = "text-danger";
        }

        if(value == 10) {
            content = "Worst pain possible";
            classes = "text-danger";
        }

        label.textContent = content + " (" + value + ")";
        label.setAttribute("class", classes);
    });
</script>

<%@include file="/WEB-INF/comps/dashboard/footer.jsp" %>
// 종일체크시 시간 활성/비활성
// 종일(all-day) 체크박스
$('#allDay').change(function () {
    if (this.checked) {
        // 종일 체크되었을 때
        $('#timeInputs').addClass('blurred');
        $('#startTime').prop('disabled', true);
        $('#endTime').prop('disabled', true);
    } else {
        // 종일 체크가 해제되었을 때
        $('#timeInputs').removeClass('blurred');
        $('#startTime').prop('disabled', false);
        $('#endTime').prop('disabled', false);
    }
});

// 첫 시작시 종일(all-day) 체크박스 블러처리 
$(document).ready(function() {
    $('#deletePlan').hide();
    // 페이지 로드 시 종일 체크 여부 확인
    if ($('#allDay').is(':checked')) {
        // 종일 체크되어 있을 때
        $('#timeInputs').addClass('blurred'); // 시간 설정 부분을 블러 처리
        $('#startTime').prop('disabled', true);
        $('#endTime').prop('disabled', true);
    }else {
        // 종일 체크가 해제되었을 때
        $('#timeInputs').removeClass('blurred');
        $('#startTime').prop('disabled', false);
        $('#endTime').prop('disabled', false);
    }
});

// 시간설정 15분단위(24시간)
function populateTimeDropdown(selectElement) {
    for (let hour = 0; hour < 24; hour++) {
        for (let minute = 0; minute < 60; minute += 30) {
            const formattedHour = hour.toString().padStart(2, '0');
            const formattedMinute = minute.toString().padStart(2, '0');
            const optionText = `${formattedHour}:${formattedMinute}`;

            const optionElement = document.createElement('option');
            optionElement.value = optionText;
            optionElement.textContent = optionText;

            selectElement.appendChild(optionElement);
        }
    }
}

// 시작 시간과 종료 시간 드롭다운을 채우기
const startTimeDropdown = document.getElementById('startTime');
const endTimeDropdown = document.getElementById('endTime');

populateTimeDropdown(startTimeDropdown);
populateTimeDropdown(endTimeDropdown);


// startTime 드롭다운에 이벤트 리스너 추가
startTimeDropdown.addEventListener('change', function() {
    const selectedStartTime = this.value; // 선택된 시작 시간 가져오기
    const selectedEndTime = endTimeDropdown.value; // 선택된 종료 시간 가져오기

    // 종료 시간 업데이트
    updateEndTime(selectedStartTime, selectedEndTime);
});

// endTime 드롭다운에 이벤트 리스너 추가
endTimeDropdown.addEventListener('change', function() {
    const selectedStartTime = startTimeDropdown.value; // 선택된 시작 시간 가져오기
    const selectedEndTime = this.value; // 선택된 종료 시간 가져오기

    // 시작 시간 업데이트
    updateStartTime(selectedStartTime, selectedEndTime);
});

// 선택된 시작 시간을 기반으로 종료 시간 업데이트
function updateEndTime(selectedStartTime, selectedEndTime) {
    const momentStartTime = moment(selectedStartTime, 'HH:mm');
    const momentEndTime = moment(selectedEndTime, 'HH:mm');
    const duration = momentEndTime.diff(momentStartTime, 'minutes');

    // 시작 시간과 종료 시간의 차이가 30분보다 작으면 종료 시간 업데이트
    if (duration < 30) {
        const newEndTime = momentStartTime.clone().add(30, 'minutes');
        endTimeDropdown.value = newEndTime.format('HH:mm');
    }
}

// 선택된 종료 시간을 기반으로 시작 시간 업데이트
function updateStartTime(selectedStartTime, selectedEndTime) {
    const momentStartTime = moment(selectedStartTime, 'HH:mm');
    const momentEndTime = moment(selectedEndTime, 'HH:mm');
    const duration = momentEndTime.diff(momentStartTime, 'minutes');

    // 종료 시간과 시작 시간의 차이가 30분 이상이면 그대로 시작 시간 유지
    if (duration >= 30) {
        startTimeDropdown.value = selectedStartTime;
    } else {
        // 종료 시간과 시작 시간의 차이가 30분보다 작으면 시작 시간을 종료 시간 - 30으로 업데이트
        const newStartTime = momentEndTime.clone().subtract(30, 'minutes');
        startTimeDropdown.value = newStartTime.format('HH:mm');
    }
}
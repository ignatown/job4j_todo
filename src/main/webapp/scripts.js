function validate() {
    let elements = document.forms[0].elements;
    for (let i = 0; i < elements.length - 1; i++) {
        if ($(elements[i]).val() === '') {
            alert($(elements[i]).attr('title'));
            return false;
        }
    }
    return true;
}
function showAllTask() {
    return window.location.href = "/todo/task?show=all";
}
function showOffTask() {
    return window.location.href = "/todo/task?show=off";
}
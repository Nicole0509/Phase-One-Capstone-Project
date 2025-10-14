// Setting up the connection to the API

// Toggle Sections
function showSection(sectionId){
    document.querySelectorAll('.content-section').forEach(sec => sec.classList.add('hidden'));
    document.getElementById(sectionId).classList.remove('hidden');
}

document.getElementById("menu1").onclick = function(){
    toogleMenuActive("menu1");
};
function toogleMenuActive(elementId){
    let activeElement = document.getElementById(elementId);
    let passiveElements = document.getElementsByClassName("nav-item");
    for(let i  = 0; i < passiveElements.length; i++){
        if(activeElement === passiveElements[i]){
            passiveElements[i].ClassList.add("active");
        }else{
            if(passiveElements[i].classList.contains("active")){
                passiveElements[i].ClassList.remove("active");
            }
        }
    }
}

function printListNewBooks(){
    let cards = '';
    
}
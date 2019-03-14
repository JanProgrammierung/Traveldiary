window.onload = function() {

           var arrow = document.querySelector("span.glyphicon.glyphicon-arrow-down");

            arrow.onclick = function() {
                var inputName = document.querySelector("input#name");
                inputName.style.display = "block";
            }

            var deleteForm = document.getElementById("deleteForm")

            deleteForm.addEventListener("submit",function(event){
               if(confirm("Sind Sie sicher, dass Sie das löschen wollen?") === false)
               {
                    event.preventDefault();
               }
            });
        };
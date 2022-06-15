
function getField() {
    const interval = setInterval(() => {

        fetch('http://localhost:8081/fields/msg')
                    .then(response => response.text())
                       .then((response) => {
                        document.getElementById("msg").innerHTML = response;
        }).catch(err => console.error(err));

        fetch('http://localhost:8081/fields/')
            .then(result => result.json())
            .then((output) => {
                document.getElementsByClassName("gameField")[0].replaceChildren();
                for (i = 0; i < output[0].length; i++) {
                    for (j = 0; j < output[1].length; j++) {
                        if (output[i][j] == 'X') {
                            let div = document.createElement("div");
                            div.className = "fieldElementX";
                            document.getElementsByClassName("gameField")[0].appendChild(div);
                        } else {
                            let div = document.createElement("div");
                            div.className = "fieldElement";
                            document.getElementsByClassName("gameField")[0].appendChild(div);
                        }
                    }
                }
            }).catch(err => console.error(err));
    }, 200);
}


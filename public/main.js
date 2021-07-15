let table = document.getElementById("chess")

let timeouts = []

let tableText = (p, q) => {
    return () => {
        timeouts.forEach((timer) => {
            clearTimeout(timer)
        })

        var cells = document.querySelectorAll("td")

        for (var i = 0; i < cells.length; i++) cells[i].innerHTML = ""

        let tour = JSON.parse(chesstour(p, q))

        for (let x = 0; x < tour.length; x++) {
            let tick = () => {
                return () => {
                    (document.getElementById("chess").rows[tour[x].i].cells)[tour[x].j].innerHTML =
                        `<span style='font-weight: bold;color: rgb(175, 11, 11);'>${x + 1}</span>`
                }
            }
            timeouts.push(setTimeout(tick(), 1000 * x))
        }
    }
}

for (let i = 0; i < 10; i++) {

    let tr = document.createElement('tr')

    tr.setAttribute('data-row', i)

    for (let j = 0; j < 10; j++) {

        let td = document.createElement('td')

        td.addEventListener("click", tableText(i, j))

        if (i == 9) td.setAttribute('data-col', j)

        if (i % 2 == j % 2) td.className = "white"
        else td.className = "black"

        tr.appendChild(td)
    }

    table.appendChild(tr)
}

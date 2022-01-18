const retVal = []
const currentVal = []

function backTracking(n, k, startIndex) {

    if (currentVal.length === k) {
        retVal.push([...currentVal])
        return
    }

    for (let i = startIndex; i <= n ; i++) {
        currentVal.push(i)
        backTracking(n, k, i + 1)
        currentVal.pop()
    }

}

function combine(n, k) {
    backTracking(n, k, 1)
    return retVal
}

console.log(combine(4, 2))
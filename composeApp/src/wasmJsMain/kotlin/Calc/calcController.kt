import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.integer.BigInteger

private fun BigDecimal.pow(exponent: BigDecimal): BigDecimal {
    return BigDecimal.parseString("0")
}

class calcController  {
    private var input = ""
    var runningCalculation: String = ""
        private set
    private var operatorDisable: Boolean
    private var onEqual = false
    private var calcString: HashMap<Operation, String>? = null
    private var numberBase: NumberBase
    private var value: BigDecimal = BigDecimal.parseString("0")
    private var isScientific = false
    private var numberFormat: String? = null
    private var scientificNumberFormat: String? = null
    private var onError = false
    private var error = ""

    enum class NumberBase {
        DECIMAL, HEXIDECIMAL, BINARY
    }
    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, SQUARE, SQUAREROOT, POWER, FACTORIAL, SINE, COSINE, TAN, LOG, EMPTY
    }

    private var operationHold = Operation.EMPTY

    init {
        setDPrecision(8)
        operationMap()
        operatorDisable = true
        numberBase = NumberBase.DECIMAL
    }

    private fun operationMap() {
        calcString = HashMap()
        calcString!![Operation.ADD] = "+"
        calcString!![Operation.SUBTRACT] = "-"
        calcString!![Operation.MULTIPLY] = "*"
        calcString!![Operation.POWER] = "^"
        calcString!![Operation.DIVIDE] = "/"
        calcString!![Operation.SQUARE] = "\u00B2"
        calcString!![Operation.FACTORIAL] = "!"
        calcString!![Operation.SINE] = "sin"
        calcString!![Operation.COSINE] = "cosine"
        calcString!![Operation.TAN] = "tan"
        calcString!![Operation.LOG] = "log"
        calcString!![Operation.SQUAREROOT] = "\u221A"
    }

    private fun formatCalcString(calcStr: String, operation: Operation): String {
        var calcStr = calcStr
        calcStr = when (operation) {
            Operation.SINE, Operation.COSINE, Operation.TAN, Operation.LOG, Operation.SQUAREROOT -> calcString!![operation] + "(" + calcStr + ")"
            Operation.SQUARE -> "(" + calcStr + ")" + calcString!![operation]
            Operation.DIVIDE, Operation.ADD, Operation.EMPTY, Operation.FACTORIAL, Operation.MULTIPLY, Operation.POWER, Operation.SUBTRACT -> calcStr + calcString!![operation]
        }
        return calcStr
    }

    private fun PerformOperator(operation: Operation) {
        if (numberBase.equals(NumberBase.HEXIDECIMAL) && !(input.equals(""))) {
            input = hexToDec(input)
        } else if (numberBase.equals(NumberBase.BINARY) && !(input.equals(""))) {
            input = binaryToDec(input)
        }
        when (operation) {
            Operation.ADD -> value = value.add(BigDecimal.parseString(input))
            Operation.COSINE -> value = (BigDecimal.parseString(value.doubleValue().toString()))
            Operation.DIVIDE -> value = value.divide(BigDecimal.parseString(input))
            Operation.EMPTY -> {
                if (input !== "") {
                    value = value.add(BigDecimal.parseString(input))
                }
            }

            Operation.FACTORIAL -> value = fac(value, value)
            Operation.LOG -> value = (BigDecimal.parseString(value.toString()))
            Operation.MULTIPLY -> value = value.multiply(BigDecimal.parseString(input))
            Operation.POWER -> value = value.pow(BigDecimal.parseString(input))
            Operation.SINE -> value = ((BigDecimal.parseString(value.doubleValue().toString())))
            Operation.SQUARE -> value = value.multiply(value)
            Operation.SQUAREROOT -> if ((value.toString()) >= 0.toString()) {
                value = sqrt(value, 10)
            } else {
                throw ArithmeticException("Error: Cannot Square Root Negative")
            }

            Operation.SUBTRACT -> value = value.subtract(BigDecimal.parseString(input))
            Operation.TAN -> value = (BigDecimal.parseString(value.doubleValue().toString()))
        }
    }

    fun operationInitiated(operation: Operation?) {
        onError = false
        if (!operatorDisable) {
            when (operation) {
                Operation.ADD, Operation.SUBTRACT, Operation.MULTIPLY, Operation.DIVIDE, Operation.EMPTY, Operation.POWER -> {
                    operatorDisable = true
                    onEqual = false
                    runningCalculation = formatCalcString(runningCalculation, operation)
                    if (!tryCompute(operationHold)) {
                        return
                    }
                    operationHold = operation
                }

                Operation.COSINE, Operation.FACTORIAL, Operation.LOG, Operation.SINE, Operation.SQUARE, Operation.SQUAREROOT, Operation.TAN -> {
                    runningCalculation = formatCalcString(runningCalculation, operation)
                    if (!(input.equals(""))) {
                        if (!tryCompute(operationHold)) {
                            return
                        }
                    }
                    if (!tryCompute(operation)) {
                        return
                    }
                    operationHold = Operation.EMPTY
                }

                else -> {}
            }
            input = ""
        }
    }

    private fun tryCompute(op: Operation): Boolean {
        try {
            PerformOperator(op)
        } catch (ex: ArithmeticException) {
            return false
        } catch (ex: NumberFormatException) {
            return false
        }
        return true
    }

    private fun fac(n: BigDecimal, acc: BigDecimal): BigDecimal {
        if (n.equals(BigDecimal.ONE)) {
            return acc
        }
        return fac(n.subtract(BigDecimal.ONE), acc.multiply(n.subtract(BigDecimal.ONE)))
    }

    private val totalStringFormatted: String
        get() {
            val formattedTotal: String = if (!isScientific) {
                value.toStringExpanded()
            } else {
                value.toString()
            }
            return formattedTotal
        }

    private fun hexToDec(hex: String): String {
        return BigInteger.parseString(hex, 16).toString()
    }

    private fun decToHex(dec: String): String {
        return BigDecimal.parseString(dec).toBigInteger().toString(16)
    }

    private fun decToBinary(dec: String): String {
        return BigDecimal.parseString(dec).toBigInteger().toString(2)
    }

    private fun binaryToDec(bin: String): String {
        return BigInteger.parseString(bin, 2).toString()
    }

    private fun formatHexString(hexString: String): String {
        val string = StringBuilder(hexString)
        var idx: Int = string.length - 4
        while (idx > 0) {
            string.insert(idx, " ")
            idx = idx - 4
        }
        return string.toString()
    }
    fun getRunningCalculation(): String {
        return runningCalculation
    }
    private fun errorRecovery(error: String) {
        clearCalculatorPressed()
        this.error = error
        onError = true
    }

    val getFormattedDisplay: String
        get() {
            var total = ""
            if (onError) {
                total = error
            } else {
                when (numberBase) {
                    NumberBase.BINARY -> total = formatHexString(decToBinary(value.toString()))
                    NumberBase.DECIMAL -> total = totalStringFormatted
                    NumberBase.HEXIDECIMAL -> total = formatHexString(decToHex(value.toString()))
                }
            }
            return total
        }

    fun setDPrecision(p: Int) {
        numberFormat = "###,###."
        scientificNumberFormat = "0."
        for (i in p downTo 1) {
            numberFormat = numberFormat.toString() + "#"
            scientificNumberFormat = scientificNumberFormat.toString() + "#"
        }
        scientificNumberFormat = scientificNumberFormat.toString() + "E0"
    }


    fun numberPressed(num: String) {
        if (!(num.equals(".") && input.contains("."))) {
            operatorDisable = false
            input = input + num
            runningCalculation = runningCalculation + num
        }
    }

    fun equalPressed() {
        if (!onEqual) {
            if (!tryCompute(operationHold)) {
                return
            }
            operationHold = Operation.EMPTY
            input = ""
            onEqual = true
        }
        operatorDisable = false
    }

    fun negateInputPressed() {
        if (!input.equals("")) // only negate if there is number in input
        {
            input = "-$input"
            runningCalculation =
                StringBuilder(runningCalculation).insert(runningCalculation.length - input.length + 1, "-")
                    .toString()
        }
    }

    fun clearCalculatorPressed() {
        value = BigDecimal.parseString("0")
        operationHold = Operation.EMPTY
        runningCalculation = ""
        input = ""
        operatorDisable = true
        onEqual = false
    }

    fun changeNumberBase(base: NumberBase) {
        val oldBase: NumberBase = numberBase
        numberBase = base
        if (!(input.equals(""))) {
            if (oldBase.equals(NumberBase.HEXIDECIMAL)) {
                input = hexToDec(input)
            } else if (oldBase.equals(NumberBase.BINARY)) {
                input = binaryToDec(input)
            }
            if (base.equals(NumberBase.HEXIDECIMAL)) {
                input = decToHex(input)
            } else if (base.equals(NumberBase.BINARY)) {
                input = decToBinary(input)
            }
        }
    }

    fun enterExpression(expression: String?) {
    }

    fun useScientificNotation(x: Boolean) {
        isScientific = x
    }


    fun setPrecision(precision: Int) {
        setDPrecision(precision)
    }


    fun submitOperation(op: Operation?) {
    }

    companion object {
        private fun sqrt(`in`: BigDecimal, scale: Int): BigDecimal {

            return BigDecimal.parseString("0")
        }
    }
}

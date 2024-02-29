package yahtzee.kotlin

import ScoreTypes

object Rules {
    private val funMap = mapOf(
        ScoreTypes.one to Rules::getOneScore,
        ScoreTypes.two to Rules::getTwoScore,
        ScoreTypes.three to Rules::getThreeScore,
        ScoreTypes.four to Rules::getFourScore,
        ScoreTypes.five to Rules::getFiveScore,
        ScoreTypes.six to Rules::getSixScore,
        ScoreTypes.threeKind to Rules::getThreeKindScore,
        ScoreTypes.fourKind to Rules::getFourKindScore,
        ScoreTypes.fullHouse to Rules::getFullHouseScore,
        ScoreTypes.smallStraight to Rules::getFourRowScore,
        ScoreTypes.largeStraight to Rules::getFiveRowScore,
        ScoreTypes.Yahtzee to Rules::getFiveKindScore,
        ScoreTypes.Chance to Rules::getChanceScore
    )


    fun getScore(scoreType: ScoreTypes, dice: IntArray): Int {
        return funMap[scoreType]?.invoke(dice) ?: 0
    }

    private fun getOneScore(dice: IntArray): Int {
        return getNumCount(dice, 1)
    }

    private fun getTwoScore(dice: IntArray): Int {
        return getNumCount(dice, 2)
    }

    private fun getThreeScore(dice: IntArray): Int {
        return getNumCount(dice, 3)
    }

    private fun getFourScore(dice: IntArray): Int {
        return getNumCount(dice, 4)
    }

    private fun getFiveScore(dice: IntArray): Int {
        return getNumCount(dice, 5)
    }

    private fun getSixScore(dice: IntArray): Int {
        return getNumCount(dice, 6)
    }

    private fun getThreeKindScore(dice: IntArray): Int {
        return getKindScore(dice, 3)
    }

    private fun getFourKindScore(dice: IntArray): Int {
        return getKindScore(dice, 4)
    }

    private fun getFiveKindScore(dice: IntArray): Int {
        return getKindScore(dice, 5)
    }

    private fun getKindScore(dice: IntArray, kind: Int): Int {
        var num = 0
        for (i in 0..6) {
            if (getNumCount(dice, i, kind) > num) {
                num = if (kind == 5) {
                    50
                } else {
                    getChanceScore(dice)
                }
            }
        }
        return num
    }

    private fun getFullHouseScore(dice: IntArray): Int {
        var num = 0
        var count = 0
        var d = intArrayOf(0, 0, 0, 0, 0)
        val z = d
        for (i in 1..6) {
            for (j in 0..4) {
                if (dice[j] == i) {
                    count++
                } else {
                    d[num] = dice[j]
                    num++
                }
            }
            if (count >= 3) {
                if (d[0] == d[1]) {
                    return 25
                }
            }
            count = 0
            d = z
            num = 0
        }
        return 0
    }

    private fun getFourRowScore(dice: IntArray): Int {
        return getRowScore(dice, row.smallStraight)
    }

    private fun getFiveRowScore(dice: IntArray): Int {
        return getRowScore(dice, row.largeStraight)
    }

    private fun getRowScore(d: IntArray, row: row): Int {
        var s = 0
        var count = 0
        d.sort()
        for (i in 0..3) {
            if (d[i + 1] - d[i] == 1) {
                count++
            }
        }
        if (row == Rules.row.smallStraight && count >= 3) {
            s = 30
        } else if (row == Rules.row.largeStraight && count == 4) {
            s = 40
        }
        return s
    }

    private fun getNumCount(dice: IntArray, num: Int): Int {
        var count = 0
        for (i in 0..4) {
            if (dice[i] == num) {
                count++
            }
        }
        return count * num
    }

    private fun getChanceScore(dice: IntArray): Int {
        var score = 0
        for (i in 0..4) {
            score += dice[i]
        }
        return score
    }

    private fun getNumCount(dice: IntArray, num: Int, count: Int): Int {
        var score = 0
        var c = 0
        for (i in 0..4) {
            if (dice[i] == num) {
                c++
            }
        }
        if (c >= count) {
            score = c * num
        }
        return score
    }

    private enum class row {
        smallStraight, largeStraight
    }
}
package com.iamsmkr.chesstour.heuristic.models

case class Pos(i: Int, j: Int)

object Pos {
  val allPos: IndexedSeq[Pos] = for {x <- 0 until 10; y <- 0 until 10} yield Pos(x, y)

  def getNextPossiblePos(currPos: Pos): List[Pos] = {
    val Pos(i, j) = currPos

    List(
      Pos(i - 2, j - 2), Pos(i - 3, j), Pos(i - 2, j + 2),
      Pos(i, j - 3), Pos(i, j + 3),
      Pos(i + 2, j - 2), Pos(i + 3, j), Pos(i + 2, j + 2)
    ).filter { case Pos(x, y) =>
      x >= 0 && x <= 9 && y >= 0 && y <= 9
    }
  }

  // Get next possible position with least further possible positions available to move to out of all next available
  // possible positions.

  def getMinimalDegreeNextPossiblePos(nextPossiblePos: List[Pos], alreadyVisitedPos: List[Pos]): (Option[Pos], List[Pos]) = {
    if (nextPossiblePos.isEmpty) return (None, Nil)

    val leastPos = nextPossiblePos.map { n => (n, getNextPossiblePos(n) diff alreadyVisitedPos) }.minBy { case (_, p) => p.size }

    val maybeNextPossiblePos = Some(leastPos._1)
    val otherPossiblePos = leastPos._2.filter(_ != maybeNextPossiblePos.get)

    (maybeNextPossiblePos, otherPossiblePos)
  }

}

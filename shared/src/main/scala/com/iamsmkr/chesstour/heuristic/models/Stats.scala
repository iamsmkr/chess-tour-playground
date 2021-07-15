package com.iamsmkr.chesstour.heuristic.models

import com.iamsmkr.chesstour.heuristic.models.Pos._

import scala.annotation.tailrec

// Stats hold relevent information to facilitate tail recursive backtracking for every position in a given board.

case class Stats(alreadyVisitedPos: List[Pos], maybeNextPossiblePos: Option[Pos], otherPossiblePos: List[Pos])

object Stats {

  // Get last stats in the stack that has non empty other possible positions to move to because there is no way to continue
  // further with empty other possible positions.

  @tailrec
  def getLastStats(stats: List[Stats]): List[Stats] = {
    if (stats.head.otherPossiblePos.isEmpty) getLastStats(stats.tail) else {

      val Stats(alreadyVisitedPos, _, _otherPossiblePos) = stats.head
      val (maybeNextPossiblePos, otherPossiblePos) = getMinimalDegreeNextPossiblePos(_otherPossiblePos, alreadyVisitedPos)

      Stats(alreadyVisitedPos, maybeNextPossiblePos, otherPossiblePos) :: stats.tail
    }
  }

}

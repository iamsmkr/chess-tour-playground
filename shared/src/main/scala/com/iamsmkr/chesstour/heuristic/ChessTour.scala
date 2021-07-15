package com.iamsmkr.chesstour.heuristic

import com.iamsmkr.chesstour.heuristic.models.{Pos, Stats}
import com.iamsmkr.chesstour.heuristic.models.Pos._
import com.iamsmkr.chesstour.heuristic.models.Stats._

import scala.annotation.tailrec

// Based on Warnsdorff Heuristic Algorithm

object ChessTour {

  def solve(currPos: Pos): List[Pos] = {

    @tailrec
    def compute(stats: List[Stats]): List[Pos] = {
      if (stats.isEmpty) Nil
      else {
        val currStats = stats.head

        val unvisitedPos = allPos diff currStats.alreadyVisitedPos

        if (unvisitedPos.isEmpty) currStats.alreadyVisitedPos.reverse
        else {
          if (currStats.maybeNextPossiblePos.isDefined) {
            val nextPos = currStats.maybeNextPossiblePos.get

            val alreadyVisitedPos = nextPos :: currStats.alreadyVisitedPos
            val possiblePos = getNextPossiblePos(nextPos) diff alreadyVisitedPos
            val (maybeNextPossiblePos, otherPossiblePos) = getMinimalDegreeNextPossiblePos(possiblePos, alreadyVisitedPos)

            val newStats = Stats(alreadyVisitedPos, maybeNextPossiblePos, otherPossiblePos)
            compute(newStats :: stats)
          } else compute(getLastStats(stats.tail))
        }
      }
    }

    val possiblePos = getNextPossiblePos(currPos)
    val alreadyVisitedPos = List(currPos)
    val (maybeNextPossiblePos, otherPossiblePos) = getMinimalDegreeNextPossiblePos(possiblePos, alreadyVisitedPos)

    val stats = List(Stats(alreadyVisitedPos, maybeNextPossiblePos, otherPossiblePos))
    compute(stats)
  }

}

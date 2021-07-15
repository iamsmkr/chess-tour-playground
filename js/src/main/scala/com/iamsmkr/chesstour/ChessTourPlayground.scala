package com.iamsmkr.chesstour

import com.iamsmkr.chesstour.heuristic.ChessTour
import com.iamsmkr.chesstour.heuristic.ChessTour.Pos
import upickle.default

import scala.scalajs.js.annotation._

object ChessTourPlayground {
  implicit val posRW: default.ReadWriter[Pos] = upickle.default.macroRW[Pos]

  @JSExportTopLevel("chesstour")
  def chesstour(i: Int, j: Int): String = upickle.default.write[List[Pos]](ChessTour.solve(Pos(i, j)))

}

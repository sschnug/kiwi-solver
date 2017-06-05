package kiwi.modeling;

import java.util.HashMap;

import kiwi.util.Tuple;
import kiwi.variable.IntVar;
import kiwi.variable.IntVarOffset;
import kiwi.variable.IntVarOpposite;

public class Views {

  // Contains all the opposite views.
  private static HashMap<IntVar, IntVar> oppositeViews = new HashMap<>();
  
  // Contains all the offset views.
  private static HashMap<Tuple<IntVar, Integer>, IntVar> offsetViews = new HashMap<>();
  
  public static IntVar opposite(IntVar x) {
    IntVar view = oppositeViews.get(x);
    if (view == null) {
      view = new IntVarOpposite(x);
      oppositeViews.put(x, view);
      oppositeViews.put(view, x);
    }
    return view;
  }

  public static IntVar offset(IntVar x, int k) {
    Tuple<IntVar, Integer> t = new Tuple<>(x, k);
    IntVar view = offsetViews.get(x);
    if (view == null) {
      view = new IntVarOffset(x, k);
      offsetViews.put(t, view);
      t = new Tuple<>(view, -k);
      offsetViews.put(t, x);
    }
    return view;
  }
}
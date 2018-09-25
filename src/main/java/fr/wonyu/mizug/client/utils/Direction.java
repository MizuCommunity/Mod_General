package fr.wonyu.mizug.client.utils;

import net.minecraft.util.math.MathHelper;

public class Direction
{
  public static final int[] offsetX = { 0, -1, 0, 1 };
  public static final int[] offsetZ = { 1, 0, -1, 0 };
  public static final String[] directions = { "SOUTH", "WEST", "NORTH", "EAST" };
  public static final int[] directionToFacing = { 3, 4, 2, 5 };
  public static final int[] facingToDirection = { -1, -1, 2, 0, 1, 3 };
  public static final int[] rotateOpposite = { 2, 3, 0, 1 };
  public static final int[] rotateRight = { 1, 2, 3, 0 };
  public static final int[] rotateLeft = { 3, 0, 1, 2 };
  public static final int[][] bedDirection = { { 1, 0, 3, 2, 5, 4 }, { 1, 0, 5, 4, 2, 3 }, { 1, 0, 2, 3, 4, 5 }, { 1, 0, 4, 5, 3, 2 } };
  private static final String __OBFID = "CL_00001506";
  
  public static int getMovementDirection(double par0, double par2)
  {
    return par2 > 0.0D ? 2 : MathHelper.abs((float)par0) > MathHelper.abs((float)par2) ? 3 : par0 > 0.0D ? 1 : 0;
  }
}
package playgroundOO

object Enums {

  // created a sealed type
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // add fields/methods
    def openDocument(): Unit =
      if (this == READ) println("opening document...")
      else println("Reading not allowed")
  }

  val somePermissions: Permissions = Permissions.READ
  val anotherPermission = Permissions.WRITE

  // constructor arguments
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = // whatever
      PermissionsWithBits.NONE
  }

  // standard API
  val somePermissionsOrdinal = somePermissions.ordinal
  val allPermissions = PermissionsWithBits.values
  val readPermission: Permissions = Permissions.valueOf("READ")


  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    anotherPermission.openDocument()
    println(somePermissionsOrdinal)
    // println(allPermissions)
  }

}

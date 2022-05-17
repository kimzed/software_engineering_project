```mermaid
sequenceDiagram
    participant Member
    participant MemberListGUI

    Member -) MemberListGUI: Open MemberListGUI
    MemberListGUI ->> MemberList: Reads all member names
    MemberList -->> MemberListGUI: Pass names
    MemberListGUI -->> Member: Displays member names
    Member ->> MemberListGUI: Search member by name/e-mail/date of birth
    MemberListGUI ->> MemberList: searchText()
    MemberList -->> MemberListGUI: Passes name, e-mail, date of birth
    MemberListGUI -->> Member: Displays name, e-mail, date of birth
  
```

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <h1>Gathr</h1>
    <p>Microsoft Teams Clone</p>
    <h5>About the app</h5>
    <h4>Video Conferencing</h4>
    <p>Gathr is a mobile app that is focused to provide connectivity + productivity the users.
        User can make a video conference meets with upto 72 people.Video conference in the app includes these</p>
        <ul>
            <li>Lobby Mode(blocking further entry of users)</li>
            <li>Adding to the meeting</li>
            <li>Lower|Raise hand</li>
            <li>Screen Sharing</li>
            <li>Invite people with web meeting(participants can join meeting from web using a link shared from inside the meeting)</li>
            <li>Tile View</li>
            <li>Mute all participants</li>
            <li>Disable everyone's camera</li>
            <li>Picture-in-Picture(PIP)</li>
            <li>Multitask in PIP mode</li>
            <li>Inbuilt confidential chat feature ie user can chat there till the meeting is going on and data will be wiped off once meeting is over</li>
            <li>Enabling audio mode only(help with low bandwidth connections)</li>
        </ul>
    <h4>In-app One-to-one Chat</h4>
    <p> Apart from these <i>Gathr</i> also provides in-app one-to-one chat option when user wants to chat before and after the meeting.(Can also be used during the meeting by converting ongoing meeting to PIP mode)</p>
   <h4>Bulletin</h4>
    <p>One feature I personally think should be <i>Microsoft Teams</i> is bulletins so i included that in Gathr user can add and delete tasks to bulletin which helps in remain productive and informed. </p>
  <h4>Reminders</h4>
    <p>Whats the use of bulletin if you forget to open the app on time?
        This is where reminders come handy to help you be on time.
        They are easy to set and just a touch away to delete thus helping you set personal alarm to avoid those late night assignment submission.</p> 
    <h4>UI/UX</h4>
   <p>Gathr have intuitive ui/ux to make the app not just be user-friendly but also fast to browse.</p>
    <br>
   <h2>Agile Methodology</h2>
        As part of Microsoft Engage '21 program , I used Agile Methodology to build this app
    <ul><li>   Tech Stack : Android - Kotlin</li>
      <li> IDE : Android Studio</li>
      <li> Version Control: GitHub</li>
       <li>Libraries : Groupie(For setting up adapters)</li>
        <br>
   <h4>Sprint 1: Design Phase</h4>
        <table style="border-style: solid;">
            <tr>
                <th>priority</th>
                <th>Task</th>
            </tr>
            <tr>
                <td>p0 ✅</td>
                <td>
                    Design layouts: 
                    <ul>
                        <li>Fragments</li>
                        <li>Activities</li>
                        <li>packages</li>
                        <li>layouts</li>
                        <li>storing color schemes and themes</li>
                        <li>Assigning ids</li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>p1 ✅</td>
                <td>
                    <ul>
                        <li>Research for video conferencing</li>
                        <li>Finding appropraite sdk/api 
                            <ol>
                                <li>WebRTC (open source)</li>
                                <li>Jitsi (Free to use sdk)</li>
                                <li>Agora etc (Paid)</li>
                                <li>ACS (Free for now)</li>
                            </ol>
                        </li>
                    </ul>
                </td>
                <td>Jitsi was the best suited sdk to my needs keeping the time constraints in mind.</td>
            </tr>
        </table>
        <p>Issues faced: Getting familiar with fragments </p>
        <br>
   <h4>Sprint 2: Built Phase</h4>
        <table>
            <tr>
                <th>priority</th>
                <th>Task</th>
            </tr>
            <tr>
                <td>p0 ✅</td>
                <td>Consuming jitsi sdk , constant integration of different versions of sdk and testing them side by side.</td>
                  <td>  Using jitsi public servers.</td>
                  <td>  Making a random 8 digit room id generator and sharing it on any platform available on the user physical device.</td>
                </td>
            </tr>
            <tr>
                <td>p1 ✅</td>
                <td>Bulletin and Reminders
                    <ul>
                        <li>MVVM</li>
                        <li>Adapters</li>
                    </ul>
                </td>
                <td>Clean Architecture</td>
            </tr>
            <tr>
                <td>p2 ❌</td>
                <td>Recording feature using Jibri</td>
                <td>
                    Rent a vm and configure it appropriately inorder to use it as a jitsi server.
                </td>
                <td>
                    Rented server can be used to record and show videos to user. 
                </td>
            </tr>
        </table>
        <p>Issues faced:  </p>
        <ul>
          <li>App crashing when user doesnt put any id by themselves</li>
          <li>Reminders not setting up</li>
          <li>Reminders not getting removed</li>
          <li>Bulletin Staggered layout not working properly</li>
          <li>Sharing button not opening tray</li>
          <li>Recording feature not working</li>
          <li>Not able to setup to private server</li>
        </ul>
        <br>
   <h4>Sprint 3: Adapt phase</h4>
       Although there was a chatting feature embedded on the jitsi meet sdk but it didn't fulfill the requirement. So had make a chat app from scratch. 
        <table>
            <tr>
                <th>priority</th>
                <th>Task</th>
            </tr>
            <tr>
                <td>p0 ✅</td>
                <td>Make a login page and integrate Firebase.
                    Design chat app structure + layouts.
                    One-to-One chat functionality.
                </td>
            </tr>
            <tr>
                <td>p1 ✅</td>
                <td>
                    Show user chats and last message from either.
                </td>
            </tr>
            <tr>
                <td>p2 ❌</td>
                <td>Group chat</td>
                <td>Team Bulletin:- Add , view , update , delete bulletin in realtime with a team bulletin.</td>
                <td>MIC and Camera testing feature</td>
            </tr>
        </table>
        <p>Issues faced:  </p>
        <ul>
          <li>Android Studio update bug , had to revert back to older version.</li>
          <li>Parcelizable class not working had to use Serialiazble to send object among the activities</li>
          <li>Space low on my system due to which emulator was able to run</li>
          <li>Chat structuring becoming quite complex and hard to understand</li>
          <li>User not able to register in the app</li>
        </ul>
        <br>
        <h4>Sprint 4: Submission Phase</h4>
            <table>
                <tr>
                    <th>priority</th>
                    <th>Task</th>
                </tr>
                <tr>
                    <td>p0 ✅</td>
                    <td> <ul>
                            <li>Make a demo of the app</li>
                            <li>Make GitHub readme file</li>
                            <li>Generate APK and deploy(Google Play Store or Drive link)</li>
                        </ul> 
                    </td>
                    <td><a href="https://youtu.be/NCxPr2JpD3s">Gathr-Demo-RaghavSharma</a></td>
                    <td><a href="https://drive.google.com/file/d/1JKV5Dw8VXeazU8B2JVmp-fSSCsv6u5hq/view?usp=sharing">Gathr-release-apk</a></td>
                </tr>
            </table>
            <br>
   <h3>Concluding</h3>
        It was a journey that taught me alot about corporate and how they do make world class softwares.
        <br>
        <p>Ps: I had final exams (4th sem) from 7th June to 25th June , 2021 (colliding with the mentorship program + i even missed Assignment 1 ) due which i was not able to add some the feature which I planned earlier but I tried to make an app with all the usefull functionalities and put all my heart in it.</p>
</body>
</html>

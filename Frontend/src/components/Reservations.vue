<template>
  <v-container grid-list-md>
    <v-layout
      row
      wrap
    >
      <v-flex
        text-center
        wrap
      >
        <h1 class="ma-5 display-4">จองห้องค้นคว้าออนไลน์</h1>
      </v-flex>
      <v-flex>
        <FullCalendar
          schedulerLicenseKey="GPL-My-Project-Is-Open-Source"
          :plugins="calendarPlugins"
          defaultView='resourceTimelineDay'
          resourceGroupField='building'
          minTime='06:00:00'
          maxTime='20:00:00'
          :aspectRatio="1.5"
          :header="{
          left: 'today prev,next',
          center: 'title',
          right: 'resourceTimelineDay,resourceTimelineThreeDay,resourceTimelineWeek,listWeekAll'
          }"
          :views="{
            resourceTimelineThreeDay: {
              type: 'resourceTimeline',
              duration: { days: 3 },
              buttonText: '3 days'
            },
            listWeekAll:{
              type:'listWeek',
              durations:{week:3},
              buttonText:'listweek'
            }
            
                }"
          :resourceColumns="[{
            labelText:'Room',
            field:'title'
          }]"
          :resources="[
      { id: 'a', building: '460 Bryant', title: 'Auditorium A' },
      { id: 'b', building: '460 Bryant', title: 'Auditorium B' },
      { id: 'c', building: '460 Bryant', title: 'Auditorium C' },
      { id: 'd', building: '460 Bryant', title: 'Auditorium D' },
      { id: 'e', building: '460 Bryant', title: 'Auditorium E' },
      { id: 'f', building: '460 Bryant', title: 'Auditorium F' },
      { id: 'g', building: '564 Pacific', title: 'Auditorium G' },
      { id: 'h', building: '564 Pacific', title: 'Auditorium H' },
      { id: 'i', building: '564 Pacific', title: 'Auditorium I' },
      { id: 'j', building: '564 Pacific', title: 'Auditorium J' },
      { id: 'k', building: '564 Pacific', title: 'Auditorium K' },
      { id: 'l', building: '564 Pacific', title: 'Auditorium L' },
      { id: 'm', building: '564 Pacific', title: 'Auditorium M' },
      { id: 'n', building: '564 Pacific', title: 'Auditorium N' },
      { id: 'o', building: '101 Main St', title: 'Auditorium O' },
      { id: 'p', building: '101 Main St', title: 'Auditorium P' },
      { id: 'q', building: '101 Main St', title: 'Auditorium Q' },
      { id: 'r', building: '101 Main St', title: 'Auditorium R' },
      { id: 's', building: '101 Main St', title: 'Auditorium S' },
      { id: 't', building: '101 Main St', title: 'Auditorium T' },
      { id: 'u', building: '101 Main St', title: 'Auditorium U' },
      { id: 'v', building: '101 Main St', title: 'Auditorium V' },
      { id: 'w', building: '101 Main St', title: 'Auditorium W' },
      { id: 'x', building: '101 Main St', title: 'Auditorium X' },
      { id: 'y', building: '101 Main St', title: 'Auditorium Y' },
      { id: 'z', building: '101 Main St', title: 'Auditorium Z' }
    ]"
          :events="event"
        />
      </v-flex>
      <v-flex md12>
        <!-- <v-sheet class="md-lg-auto" max-width="1200"> -->
        <v-layout
          row
          wrap
        >
          <v-flex md12>
            <v-layout
              row
              wrap
            >
              <v-flex
                md3
                text-center
                wrap
              >
                <br />
                <h2 class="mt-2">ชื่อผู้ใช้บริการ</h2>
              </v-flex>
              <v-flex md7>
                <br />
                <v-select
                  outlined
                  multiple
                  class="mr-10"
                  :items="items"
                  item-text="name"
                  item-value="cus_id"
                  v-model="value"
                  label="Customer"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-flex>
          <v-flex md12>
            <v-layout
              row
              wrap
            >
              <v-flex
                md3
                text-center
                wrap
              >
                <br />
                <h2 class="mt-2">จุดประสงค์การใช้ห้อง</h2>
              </v-flex>
              <v-flex md7>
                <br />
                <v-select
                  outlined
                  multiple
                  class="mr-10"
                  :items="items"
                  item-text="name"
                  item-value="cus_id"
                  v-model="value"
                  label="Customer"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-flex>
          <v-flex md12>
            <v-layout
              row
              wrap
            >
              <v-flex
                md3
                text-center
                wrap
              >
                <br />
                <h2 class="mt-2">หมายเลขห้อง</h2>
              </v-flex>
              <v-flex md7>
                <br />
                <v-select
                  outlined
                  multiple
                  class="mr-10"
                  :items="items"
                  item-text="name"
                  item-value="cus_id"
                  v-model="value"
                  label="Customer"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-flex>

          <v-flex md12>
            <v-layout
              row
              wrap
            >
              <v-flex
                md3
                text-center
                wrap
              >
                <br />
                <h2 class="mt-2">เวลาเริ่มต้น</h2>
              </v-flex>
              <v-flex md7>
                <br />
                <v-select
                  outlined
                  multiple
                  class="mr-10"
                  :items="items"
                  item-text="name"
                  item-value="cus_id"
                  v-model="value"
                  label="Customer"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-flex>

          <v-flex md12>
            <v-layout
              row
              wrap
            >
              <v-flex
                md3
                text-center
                wrap
              >
                <br />
                <h2 class="mt-2">เวลาสิ้นสุด</h2>
              </v-flex>
              <v-flex md7>
                <br />
                <v-select
                  outlined
                  multiple
                  class="mr-10"
                  :items="items"
                  item-text="name"
                  item-value="cus_id"
                  v-model="endtime"
                  label="Customer"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-flex>
        </v-layout>
        <v-btn
          style="margin:0 10% 0 35%"
          x-large
          justify="center"
          outline
          color="primary"
          dark
        >
          ยืนยันการจอง
          <v-icon
            large
            dark
            right
          >mdi-checkbox-marked-circle</v-icon>
        </v-btn>
        <v-btn
          x-large
          outline
          color="blue-grey"
          dark
        >
          เคลียร์ข้อมูล
          <v-icon
            large
            dark
            right
          >mdi-cancel</v-icon>
        </v-btn>
        <!-- </v-sheet> -->
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import FullCalendar from "@fullcalendar/vue";
import resourceTimelinePlugin from "@fullcalendar/resource-timeline";
import listPlugin from "@fullcalendar/list";
export default {
  components: {
    FullCalendar
  },
  data() {
    return {
      calendarPlugins: [resourceTimelinePlugin, listPlugin],
      event: [
        {
          id: "1",
          title: "All Day Event",
          start: "2019-12-13",
          resourceId: "a",
          color: "green"
        },
        {
          id: "2",
          title: "FLEX N GATE",
          start: "2019-12-13T10:00:00",
          end: "2019-12-13T12:00:00",
          resourceId: "b"
        },
        {
          id: "3",
          title: "FLEX N GATE",
          start: "2019-12-14T10:00:00",
          end: "2019-12-14T12:00:00",
          resourceId: "c"
        }
      ]
    };
  }
};
</script>

<style lang="scss" >
@import "~@fullcalendar/core/main.css";
@import "~@fullcalendar/timeline/main.css";
@import "~@fullcalendar/resource-timeline/main.css";
@import "~@fullcalendar/list/main.css";
</style>

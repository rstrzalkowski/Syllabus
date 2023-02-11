import {Activity} from "./activity";

export interface GradePage {
  content: Grade[],
  totalPages: number,
  last: boolean
}

export interface Grade {
  activityDTO: Activity;
  grade: number;
  createdAt: Date;
}

import {Activity} from "./activity";

export interface GradePage {
  content: Grade[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface Grade {
  activityDTO: Activity
  realisationId: string
  grade: number
  comment: string
  createdAt: Date
}
